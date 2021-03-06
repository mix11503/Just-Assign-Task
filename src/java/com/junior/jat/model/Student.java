/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kittisak
 */
public class Student {

    private String name;
    private long studentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", studentId=" + studentId + '}';
    }

    public static Student login(long studentId, String password) {
        Student s = new Student();
        try {
            Connection conn = BuildConnection.getConnection();
            String SQLcmd = "select * from student where studentId = ? AND pass = ?";
            PreparedStatement pst = conn.prepareStatement(SQLcmd);
            pst.setLong(1, studentId);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            System.out.println("rs ::: " + rs);
            if (rs.next()) {
                System.out.println("name ::::: " + rs.getString("name"));
                s.setName(rs.getString("name"));
                s.setStudentId(rs.getLong("studentId"));
            } else {
                s = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

    public static ArrayList<Task> gettaskstudent(long studentId) {
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? ORDER BY t.taskDeadlineDate DESC;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                task = new Task();
                task.setStatus(rs.getString("status"));
                task.setSubjectId(rs.getString("subjectId"));
                task.setTaskCreateDate(rs.getDate("taskCreateDate"));
                task.setTaskDeadlineDate(rs.getDate("taskDeadlineDate"));
                task.setTaskDescription(rs.getString("taskDescription"));
                task.setTaskId(rs.getInt("taskId"));
                task.setTaskName(rs.getString("taskName"));
                list.add(task);
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static ArrayList getStudent(){
        ArrayList students = new ArrayList();
        Student student = new Student();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM student";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                student = new Student();
                student.setName(rs.getString("name"));
                student.setStudentId(rs.getLong("studentId"));
                students.add(student);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }
    
    public static int deleteStudent(String studentId){
        int result = 0;
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "DELETE FROM `student` WHERE `student`.`studentId` = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, studentId);
            result = pstm.executeUpdate();     
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
    
    public static void editStudent(String id,String password,String name){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "UPDATE `student` SET `pass`= ? ,`name`= ? WHERE studentId = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, password);
            pstm.setString(2, name);
            pstm.setLong(3, Long.parseLong(id));
            pstm.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static void subSubject(long id, String subjectId){
         try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO `map_st_subj`(`studentId`, `subjectId`, `approve_state`) VALUES ( ?, ?,'OK')";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setLong(1, id);
            pstm.setString(2, subjectId);

            pstm.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se);
        }
    }
     public static void UnsubSubject(long id, String subjectId){
         try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "DELETE FROM `map_st_subj` WHERE `studentId` = ? AND `subjectId` = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            
            pstm.setLong(1, id);
            pstm.setString(2, subjectId);

            pstm.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se);
        }
    }
}
