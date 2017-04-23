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
 * @author PNattawut
 */
public class Teacher {
    private long teacherId;
    private String name;

    public Teacher() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherId=" + teacherId + ", name=" + name + '}';
    }
     public static Teacher login(long id, String password) {
        Teacher t = new Teacher();
        
        try {
            Connection conn = BuildConnection.getConnection();
            String SQLcmd = "select * from teacher where teacherId = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(SQLcmd);
            pst.setLong(1, id);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();           
            if(rs.next()){
                t.setName(rs.getString("name"));
                t.setTeacherId(rs.getLong("teacherId"));
            }else{
                t = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return t;
    }
     
    public static ArrayList getTeacher(){
        ArrayList teachers = new ArrayList();
        Teacher teacher = new Teacher();
        try {
            Connection conn = BuildConnection.getConnection();
            String sqlCmd = "SELECT * FROM teacher";
            PreparedStatement pstm = conn.prepareStatement(sqlCmd);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                teacher = new Teacher();
                teacher.setName(rs.getString("name"));
                teacher.setTeacherId(rs.getLong("teacherId"));
                teachers.add(teacher);
            }            
        } catch (SQLException ex) {
            Logger.getLogger(Teacher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teachers;
    }
    
}
