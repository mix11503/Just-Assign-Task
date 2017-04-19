package com.junior.jat.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class Admin {
    public static void addUser(String id,String password,String name,String status){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "";
            if(status.equalsIgnoreCase("Student")){
                sql = "INSERT INTO `student` (`studentId`, `password`, `name`) VALUES (?, ?, ?);";
            }
            else{
                sql = "INSERT INTO `teacher` (`teacherId`, `password`, `name`) VALUES (?, ?, ?);";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, Long.parseLong(id));
            pstm.setString(2, password);
            pstm.setString(3, name);
            pstm.executeUpdate();         
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void deleteUser(String id,String status){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "";
            if(status.equalsIgnoreCase("Student")){
                sql = "DELETE FROM `student` WHERE studentId= ?;";
            }
            else{
                sql = "DELETE FROM `teacher` WHERE teacherId = ?; ";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setLong(1, Long.parseLong(id));
            pstm.executeUpdate(); 
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void editUser(String id,String password,String name,String status){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "";
            if(status.equalsIgnoreCase("Student")){
                sql = "UPDATE `student` SET `password`= ? ,`name`= ? WHERE studentId = ?;";
            }
            else{
                sql = "UPDATE `teacher` SET `password`= ? ,`name`= ? WHERE teacherId = ?;";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, password);
            pstm.setString(2, name);
            pstm.setLong(3, Long.parseLong(id));
            pstm.executeUpdate();
            System.out.println("work!!");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    
}