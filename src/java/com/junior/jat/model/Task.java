/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maypt
 */
public class Task {
    private int taskId;
    private String subjectId;
    private String taskName;
    private String taskDescription;
    private String status;
    private Date taskCreateDate;
    private Date taskDeadlineDate;

    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTaskCreateDate() {
        return taskCreateDate;
    }

    public void setTaskCreateDate(Date taskCreateDate) {
        this.taskCreateDate = taskCreateDate;
    }

    public Date getTaskDeadlineDate() {
        return taskDeadlineDate;
    }

    public void setTaskDeadlineDate(Date taskDeadlineDate) {
        this.taskDeadlineDate = taskDeadlineDate;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", subjectId=" + subjectId + ", taskName=" + taskName + ", taskDescription=" + taskDescription + ", status=" + status + ", taskCreateDate=" + taskCreateDate + ", taskDeadlineDate=" + taskDeadlineDate + '}';
    }
    
    public static ArrayList getTask(){
        ArrayList tasks = new ArrayList();
        Task task = new Task();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                task = new Task();
                task.setTaskId(rs.getInt("taskId"));
                task.setSubjectId(rs.getString("subjectId"));
                task.setTaskName(rs.getString("taskName"));
                task.setTaskDescription(rs.getString("taskDescription"));
                task.setStatus(rs.getString("status"));
                task.setTaskCreateDate(rs.getDate("taskCreateDate"));
                task.setTaskDeadlineDate(rs.getDate("taskDeadlineDate"));
                tasks.add(task);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks;
    }
    public static void insertTask(String subjectId,String taskName,String taskDescription,Date taskCreateDate,Date taskDeadlineDate){
        
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd ="INSERT INTO task (subjectId, taskName, taskDescription, status, taskCreateDate, taskDeadlineDate )"+
            "VALUES ( ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1,subjectId);
            pstm.setString(2,taskName);
            pstm.setString(3,taskDescription);
            pstm.setString(4,"1");
            pstm.setDate(5, taskCreateDate);
            pstm.setDate(6,taskDeadlineDate);
            
            pstm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        //test push
        
    }
    public static void deleteTask(int taskId){
           try{
                Connection conn = BuildConnection.getConnection();
                String sqlCmd = "DELETE FROM task WHERE taskId = ?";
                PreparedStatement pstm = conn.prepareStatement(sqlCmd);
                pstm.setInt(1, taskId);
       
                pstm.executeUpdate();
              
             
            }catch(SQLException se){
                System.out.println(se);
            }
    }
    public static void editTask(String taskName,String taskDescription,int status,Date taskDeadlineDate,int taskId){
        try {
            Connection conn = BuildConnection.getConnection();
            String updateCmd = "UPDATE task SET taskName = ? ,taskDescription = ? , status = ? "
                    + ", taskDeadlineDate = ? WHERE  taskId = ?;";
            PreparedStatement pstm = conn.prepareStatement(updateCmd);
            pstm.setString(1, taskName);
            pstm.setString(2, taskDescription);
            pstm.setInt(3, status);
            pstm.setDate(4, taskDeadlineDate);
            pstm.setInt(5, taskId);
            
            pstm.executeUpdate();
            
        }catch(SQLException se){
            System.out.println(se);
        }
    }
    public static Task getSingleTask(int taskId){
         Task task = new Task();
         try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task WHERE taskId = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, taskId);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                task.setTaskName(rs.getString("taskName"));
                task.setTaskDescription(rs.getString("taskDescription"));
                task.setStatus(rs.getString("status"));
                task.setTaskDeadlineDate(rs.getDate("taskDeadlineDate"));
                task.setTaskId(rs.getInt("taskId"));
            }
         }catch(SQLException se){
             System.out.println(se);
         }
         return task;
    }
    
}
