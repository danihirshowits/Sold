package com.mycompany.remoteserverapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            // טוען את הדרייבר במפורש
            Class.forName("org.sqlite.JDBC");
            
            // התחברות למסד הנתונים עם try-with-resources
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/yonat/db/mydb.db");
                 Statement stmt = conn.createStatement()) {
                
                // שליפת כל המשתמשים
                String sql = "SELECT id, username,password, email FROM users";
                ResultSet rs = stmt.executeQuery(sql);
                
                // כתיבת HTML לדפדפן
                out.println("<!DOCTYPE html>");
                out.println("<html><head><meta charset='UTF-8'></head><body>");
                out.println("<h2>רשימת משתמשים</h2>");
                out.println("<table border='1'>");
                out.println("<tr><th>ID</th><th>Username</th><th>Password</th><th>Email</th></tr>");
                
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td>" + rs.getString("username") + "</td>");
                    out.println("<td>" + rs.getString("password") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                out.println("</body></html>");
                
                // סגירת ResultSet
                rs.close();
                
            } catch (SQLException e) {
                out.println("<h2>שגיאה במסד הנתונים:</h2>");
                out.println("<p>" + e.getMessage() + "</p>");
                e.printStackTrace();
            }
            
        } catch (ClassNotFoundException e) {
            throw new ServletException("SQLite driver not found", e);
        }
    }
}