package com.junior.jat.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
public class Admin {
    private String name;
    private long adminId;
    
    public static void addUser(String id,String password,String name,String status){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "";
            if(status.equalsIgnoreCase("Student")){
                sql = "INSERT INTO `student` (`studentId`, `pass`, `name`) VALUES (?, ?, ?);";
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
    
    public static void createSubject(String id, String subjectName, String teacherId){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "INSERT INTO `subject` (`subjectId`, `subjectName`, `teacherId`) VALUES (?, ?, ?);";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, subjectName);
            pstm.setLong(3, Long.parseLong(teacherId));
            pstm.executeUpdate();     
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void deleteSubject(String subjectId){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "DELETE FROM `subject` WHERE `subject`.`subjectId` = ?;";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, subjectId);
            pstm.executeUpdate();     
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void editUser(String id,String pass,String name,String status){
        try{
            Connection conn = BuildConnection.getConnection();
            String sql = "";
            if(status.equals("student")){
                sql = "UPDATE `student` SET `pass` = ?, `name` = ? WHERE `student`.`studentId` = ?;";
            }
            else{
                sql = "UPDATE `teacher` SET `password` = ?, `name` = ? WHERE `teacher`.`teacherId` = ?;";
            }
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, pass);
            pstm.setString(2, name);
            pstm.setString(3, id);
            pstm.executeUpdate();     
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
