/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.junior.jat.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QuartierLatinโดโ
 */
public class BuildConnection {

    public static java.sql.Connection getConnection() throws SQLException {
        java.sql.Connection con = null;
            try {
                String dbDriver = "com.mysql.jdbc.Driver";
                String dbUrl = "jdbc:mysql://54.169.83.168/jat?useUnicode=true&characterEncoding=UTF-8";
                String user = "jatany";
                String pw = "@Nintendo64";
                Class.forName(dbDriver);
                con = DriverManager.getConnection(dbUrl, user, pw);
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(BuildConnection.class.getName()).log(Level.SEVERE, null, ex1);
            }
        return con;
    }
}
