/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.servlet;

import com.junior.jat.model.Task;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Kittisak
 */
public class Teacher_EditTaskServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String option = request.getParameter("option");
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        //long teacherId = Long.parseLong(request.getParameter("teacherId"));
        switch (option) {            
            case "sendEdit":                
                long teacherId = Long.parseLong(request.getParameter("teacherId"));                
                String taskName = request.getParameter("taskName");
                String taskDescription = request.getParameter("taskDescription");
                int status = Integer.parseInt(request.getParameter("status"));
                Date taskDeadlineDate = Date.valueOf(request.getParameter("taskDeadlineDate"));
                int result = Task.editTask(taskName, taskDescription, status, taskDeadlineDate, taskId);
                String message = "";
                if (result == 1) {
                    message = "Teacher edit successful";
                    request.setAttribute("message", message);
                } else {
                    message = "Teacher edit fail";
                    request.setAttribute("message", message);
                }
                request.setAttribute("tasks", Task.getAllTask(teacherId));
                getServletContext().getRequestDispatcher("/teacher_home.jsp").forward(request, response);
                break;            
            case "getForEdit":
                Task task = new Task();
                task = Task.getSingleTask(Integer.parseInt(request.getParameter("taskId")));
                request.setAttribute("task", task);
                getServletContext().getRequestDispatcher("/teacher_editTask.jsp").forward(request, response);
                break;
        }
        
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
