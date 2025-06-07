/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.remoteserverapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:C:/Users/yonat/db/mydb.db";
  //  private static final String DB_USER = "dani";
  //  private static final String DB_PASSWORD = "dani";
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Database driver not found", e);
        }
    }
}
