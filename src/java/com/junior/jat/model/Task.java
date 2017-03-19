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
            String sqlCmd = "SELECT * FROM jat.task;";
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
            }
        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tasks;
    }
    
}
