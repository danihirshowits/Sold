/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.remoteserverapp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Daniel
 */
public class UserDAO {
    
   public User authenticateUser(String username, String password) {
       try {
        Class.forName("org.sqlite.JDBC");
        System.out.println("SQLite JDBC Driver loaded");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }
   
    
    // אם יש צורך ב-trim
    

   

    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

    try (Connection conn = DatabaseHelper.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {


        // הדפסה של חיבור למסד נתונים
        System.out.println("Connected to DB: " + conn);

       stmt.setString(1, username);
        stmt.setString(2, password);
        
        // הדפסה של השאילתא והפרמטרים
        

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("User found in DB: " + rs.getString("username") + ", " + rs.getString("password"));
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
         //  user.setActive(rs.getBoolean("is_active"));
            return user;
        } else {
            System.out.println("No user found for: [" + username + "] [" + password + "]");
        }

    } catch (SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        e.printStackTrace();
    }

    System.out.println("Returning null from authenticateUser");
    return null; // אימות נכשל
}

    
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashPassword(user.getPassword()));
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // פונקציה להצפנת סיסמה (בסיסית - בפרויקט אמיתי תשתמש ב-BCrypt)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}
