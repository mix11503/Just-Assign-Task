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

/**
 *
 * @author Kittisak
 */
public class Subject {
    private String subjectId;
    private String subjectName;
    private long teacherId;

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
    
    public static ArrayList getSubject(long teacherId){
        ArrayList subjects = new ArrayList();
        Subject subject = new Subject(); 
        try{
        Connection conn = BuildConnection.getConnection();
        String SQLcmd = "select * from subject where teacherId = ?";
        PreparedStatement pst = conn.prepareStatement(SQLcmd);
        pst.setLong(1, teacherId);
        ResultSet rs = pst.executeQuery();
        while(rs.next()){
            subject = new Subject();
            subject.setSubjectId(rs.getString("subjectId"));
            subject.setSubjectName(rs.getString("subjectName"));
            subject.setTeacherId(rs.getLong("teacherId"));
            subjects.add(subject);
        }
        return subjects;
       }catch(SQLException se){
            System.out.println(se);
       }
        return null;
    }
    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectName=" + subjectName + ", teacherId=" + teacherId + '}';
    }
    
    public static void main(String[] args) {
        System.out.println(getSubject(53130500553L).toString());;
    }
}
