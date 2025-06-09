/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Daniel
 */
package com.mycompany.remoteserverapp;

import java.sql.*;

public class DatabaseSetup {

    public static void main(String[] args) {
        UserDAO ud=new UserDAO();
        ud.authenticateUser("admin", "aaaaa");
        // מסלול מסד הנתונים - שנה לפי הצורך
        String url = "jdbc:sqlite:./mydatabase.db"; // או השם שבחרת, לדוגמה SoldApp.db // או השם שבחרת לקובץ ה-DB, לדוגמה SoldApp.db

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to the SQLite database successfully at: " + url);

                // יצירת אובייקט Statement
                Statement stmt = conn.createStatement();

                // הרצת PRAGMA table_info לטבלת users
                ResultSet rs = stmt.executeQuery("PRAGMA table_info(users)");

                System.out.println("Columns in table 'users':");
                while (rs.next()) {
                    String columnName = rs.getString("name"); // העמודה 'name' מכילה את שם העמודה בטבלה
                    System.out.println("- " + columnName);
                }

                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
       
    
}




