/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.servlet;

import com.junior.jat.model.Student;
import com.junior.jat.model.Subject;
import com.junior.jat.model.Task;
import com.junior.jat.model.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PNattawut
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String target = "/login.jsp";
        String message = "";
        long id = Long.parseLong(request.getParameter("id"));
        String pass = request.getParameter("pass");
        Student s = Student.login(id, pass);
        Teacher t = Teacher.login(id, pass);
        if (s != null) {
            request.getSession(true).setAttribute("student", s);
            request.setAttribute("list", Student.gettaskstudent(id));
            target = "/Student_View.jsp";
        } else if (t != null) {
            request.getSession(true).setAttribute("subjects", Subject.getSubject(id));
            request.getSession(true).setAttribute("teacher", t);
            request.setAttribute("tasks", Task.getAllTask(id));
            target = "/teacher_home.jsp";
        } else {
            message = "Your ID or Password is invalid ";
            request.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(target).forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
