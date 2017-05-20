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
import java.util.List;
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

    public static ArrayList getTask() {
        ArrayList tasks = new ArrayList();
        Task task = new Task();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
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

    public static void insertTask(String subjectId, String taskName, String taskDescription, Date taskCreateDate, Date taskDeadlineDate) {

        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "INSERT INTO task (subjectId, taskName, taskDescription, status, taskCreateDate, taskDeadlineDate )"
                    + "VALUES ( ?, ?, ?, ?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, subjectId);
            pstm.setString(2, taskName);
            pstm.setString(3, taskDescription);
            pstm.setString(4, "1");
            pstm.setDate(5, taskCreateDate);
            pstm.setDate(6, taskDeadlineDate);

            pstm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        //test push

    }

    public static void deleteTask(int taskId) {
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "DELETE FROM task WHERE taskId = ?";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, taskId);

            pstm.executeUpdate();

        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    public static void editTask(String taskName, String taskDescription, int status, Date taskDeadlineDate, int taskId) {
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

        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    public static Task getSingleTask(int taskId) {
        Task task = new Task();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task WHERE taskId = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setInt(1, taskId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                task.setTaskName(rs.getString("taskName"));
                task.setSubjectId(rs.getString("subjectId"));
                task.setTaskDescription(rs.getString("taskDescription"));
                task.setStatus(rs.getString("status"));
                task.setTaskCreateDate(rs.getDate("taskCreateDate"));
                task.setTaskDeadlineDate(rs.getDate("taskDeadlineDate"));
                task.setTaskId(rs.getInt("taskId"));
                task.setSubjectId(rs.getString("subjectId"));
            }
        } catch (SQLException se) {
            System.out.println(se);
        }
        return task;
    }

    public static List<Task> getNearTask(long studentId) {
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? and t.taskDeadlineDate >= ? ORDER BY t.taskDeadlineDate ASC;;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            pst.setDate(2, new Date(System.currentTimeMillis()));
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
    public static List<Task> getSearchNearTask(long studentId, String keyword) {
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? and t.taskDeadlineDate >= ? and t.taskName like ? ORDER BY t.taskDeadlineDate ASC;;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            pst.setDate(2, new Date(System.currentTimeMillis()));
            pst.setString(3,"%"+keyword+"%");
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
    
    
    
     public static List<Task> getLatestTask(long studentId) {
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? and t.taskCreateDate = ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            pst.setDate(2, new Date(System.currentTimeMillis()));
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
     public static List<Task> getSearchLatestTask(long studentId,String keyword) {
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? and t.taskCreateDate = ? and t.taskName like ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            pst.setDate(2, new Date(System.currentTimeMillis()));
            pst.setString(3,"%"+keyword+"%");
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
    public static List<Task> getsearchTask(String keyword, long studentId){
        ArrayList<Task> list = new ArrayList();
        try {

            Task task = null;
            Connection conn = BuildConnection.getConnection();
            String sql = "SELECT t.* FROM `map_st_subj` mts JOIN `task` t ON mts.subjectId = t.subjectId WHERE mts.studentId = ? AND t.taskName like  ?;";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, studentId);
            pst.setString(2, keyword+"%");
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

    public static ArrayList getAllTask(long teacherId){
        ArrayList tasks = new ArrayList();
        Task task = new Task();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task ta JOIN subject st ON ta.subjectId = st.subjectId JOIN teacher "
                    + "te ON te.teacherId = st.teacherId WHERE te.teacherId = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setLong(1, teacherId);
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
    
    public static ArrayList getTaskInSubject(long teacherId,String subjectId){
        ArrayList tasks = new ArrayList();
        Task task = new Task();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM task ta JOIN subject st ON ta.subjectId = st.subjectId "
                    + "JOIN teacher te ON te.teacherId = st.teacherId WHERE te.teacherId = ? AND ta.subjectId = ?;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setLong(1, teacherId);
            pstm.setString(2, subjectId);
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
}
