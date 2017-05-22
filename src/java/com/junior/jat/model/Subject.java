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
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kittisak
 */
public class Subject {

    private String subjectId;
    private String subjectName;
    private long teacherId;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public static ArrayList getSubject(long teacherId) {
        ArrayList subjects = new ArrayList();
        Subject subject = new Subject();
        try {
            Connection conn = BuildConnection.getConnection();
            String SQLcmd = "select * from subject where teacherId = ?";
            PreparedStatement pst = conn.prepareStatement(SQLcmd);
            pst.setLong(1, teacherId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getString("subjectId"));
                subject.setSubjectName(rs.getString("subjectName"));
                subject.setTeacherId(rs.getLong("teacherId"));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException se) {
            System.out.println(se);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectName=" + subjectName + ", teacherId=" + teacherId + ", state=" + state + '}';
    }

    

    public static ArrayList getSubject() {
        ArrayList subjects = new ArrayList();
        Subject subject = new Subject();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM subject";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getString("subjectId"));
                subject.setSubjectName(rs.getString("subjectName"));
                subject.setTeacherId(rs.getLong("teacherId"));
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public static ArrayList getSearchSubject(String keyword) {
        ArrayList subjects = new ArrayList();
        Subject subject = new Subject();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM subject WHERE subjectName like ?";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            pstm.setString(1, "%" + keyword + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getString("subjectId"));
                subject.setSubjectName(rs.getString("subjectName"));
                subject.setTeacherId(rs.getLong("teacherId"));
                subjects.add(subject);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    public static ArrayList getSubjectWithState(long studentId) {
        ArrayList subjects = new ArrayList();
        Subject subject = new Subject();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM subject s;";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                subject = new Subject();
                subject.setSubjectId(rs.getString("subjectId"));
                subject.setSubjectName(rs.getString("subjectName"));
                subject.setTeacherId(rs.getLong("teacherId"));
                subjects.add(subject);
            }
            approvalSetter(studentId, subjects);
        pstm.close();
        conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Subject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }

    /** Set state for each Student Subject */
    private static void approvalSetter(long studentId, ArrayList<Subject> subjects) throws SQLException {
        Connection conn = BuildConnection.getConnection();
        String sqlCmd;
        PreparedStatement pstm = null;
        ResultSet rs;
        for (Subject s : subjects) {
            sqlCmd = "SELECT approve_state FROM `map_st_subj` WHERE `studentId` = ? AND `subjectId` = ?;;";
            if(pstm == null){
            pstm = conn.prepareStatement(sqlCmd);
            } else {
               pstm.clearParameters();
            }
            pstm.setLong(1, studentId);
            pstm.setString(2, s.getSubjectId());
            rs = pstm.executeQuery();
            if(rs.next()){
                s.setState(rs.getString(1));
            }
        }
        pstm.close();
        conn.close();
    }

    public static void main(String[] args) {
        System.out.println(getSubjectWithState(59130500048L));
    }
}
