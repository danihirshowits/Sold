package com.mycompany.remoteserverapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            // טוען את הדרייבר במפורש
            Class.forName("org.sqlite.JDBC");
            
            // התחברות למסד הנתונים
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/yonat/db/mydb.db");
                 Statement stmt = conn.createStatement()) {
                
                // שליפת כל המוצרים
                String sql = "SELECT id, name, description, price, stock, created_date FROM products ORDER BY name";
                ResultSet rs = stmt.executeQuery(sql);
                
                // תחילת HTML
                out.println("<!DOCTYPE html>");
                out.println("<html><head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>רשימת מוצרים</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 20px; direction: rtl; }");
                out.println("table { border-collapse: collapse; width: 100%; }");
                out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: right; }");
                out.println("th { background-color: #f2f2f2; font-weight: bold; }");
                out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
                out.println(".price { color: #2e7d32; font-weight: bold; }");
                out.println(".stock-low { color: #d32f2f; }");
                out.println(".stock-ok { color: #388e3c; }");
                out.println("</style>");
                out.println("</head><body>");
                
                out.println("<h1>רשימת מוצרים</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID</th>");
                out.println("<th>שם המוצר</th>");
                out.println("<th>תיאור</th>");
                out.println("<th>מחיר (₪)</th>");
                out.println("<th>במלאי</th>");
                out.println("<th>תאריך הוספה</th>");
                out.println("</tr>");
                
                // הצגת המוצרים
                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("id") + "</td>");
                    out.println("<td><strong>" + rs.getString("name") + "</strong></td>");
                    out.println("<td>" + rs.getString("description") + "</td>");
                    out.println("<td class='price'>" + String.format("%.2f", rs.getDouble("price")) + "</td>");
                    
                    int stock = rs.getInt("stock");
                    String stockClass = stock < 5 ? "stock-low" : "stock-ok";
                    out.println("<td class='" + stockClass + "'>" + stock + "</td>");
                    
                    out.println("<td>" + rs.getString("created_date") + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                
                // קישורים לדפים אחרים
                out.println("<hr>");
                out.println("<p>");
                out.println("<a href='users'>רשימת משתמשים</a> | ");
                out.println("<a href='orders'>רשימת הזמנות</a> | ");
                out.println("<a href='hello'>דף בית</a>");
                out.println("</p>");
                
                out.println("</body></html>");
                
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