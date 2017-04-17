/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author PNattawut
 */
public class Teacher {
    private long teacherId;
    private String password;
    private String name;

    public Teacher() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherId=" + teacherId + ", password=" + password + ", name=" + name + '}';
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
    
}
