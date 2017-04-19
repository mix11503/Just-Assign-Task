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
 * @author Kittisak
 */
public class Student {

    private String name;
    private String password;
    private long studentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", password=" + password + ", studentId=" + studentId + '}';
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
            System.out.println("rs ::: "+rs);
            if(rs.next()){
                System.out.println("name ::::: "+rs.getString("name"));
                s.setName(rs.getString("name"));
                s.setStudentId(rs.getLong("studentId"));
            }else{
                s = null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return s;
    }

}
