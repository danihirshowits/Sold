
package com.mycompany.remoteserverapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DatabaseHelper.getConnection();
            
            String checkSql = "SELECT COUNT(*) FROM users WHERE name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, name);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            checkStmt.close();

            if (count > 0) 
                // Username exists – redirect with error message
                response.sendRedirect("addUser.jsp?error=Teh worker is already registered ");
            else{
                checkSql = "SELECT COUNT(*) FROM users WHERE username = ?";
                checkStmt = conn.prepareStatement(checkSql);
                checkStmt.setString(1, username);
                rs = checkStmt.executeQuery();
                rs.next();
                count = rs.getInt(1);
                rs.close();
                checkStmt.close();
                if (count > 0) 
                // Username exists – redirect with error message
                response.sendRedirect("addUser.jsp?error= שם המשתמש כבר בשימוש");
            
                    else{
                    String sql = "INSERT INTO users (username, email, password, role,name) VALUES (?, ?, ?, ?,?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, username);
                    stmt.setString(2, email);
                    stmt.setString(3, password);
                    stmt.setString(4, role);
                    stmt.setString(5, name);
                    stmt.executeUpdate();

                    stmt.close();
                    conn.close();

                    response.sendRedirect("admin.jsp");
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("שגיאה בהוספת משתמש: " + e.getMessage());
        }
    }
}
