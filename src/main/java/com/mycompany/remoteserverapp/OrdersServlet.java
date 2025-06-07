package com.mycompany.remoteserverapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            
            Class.forName("org.sqlite.JDBC");
            
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/yonat/db/mydb.db");
                 Statement stmt = conn.createStatement()) {
                
                // שליפת הזמנות עם פרטי משתמשים ומוצרים (JOIN)
                String sql = """
                    SELECT 
                        o.id as order_id,
                        u.username,
                        u.email,
                        p.name as product_name,
                        o.quantity,
                        o.total_price,
                        o.order_date,
                        o.status
                    FROM orders o
                    JOIN users u ON o.user_id = u.id
                    JOIN products p ON o.product_id = p.id
                    ORDER BY o.order_date DESC
                """;
                
                ResultSet rs = stmt.executeQuery(sql);
                
                // HTML עם עיצוב
                out.println("<!DOCTYPE html>");
                out.println("<html><head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>רשימת הזמנות</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 20px; direction: rtl; }");
                out.println("table { border-collapse: collapse; width: 100%; }");
                out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: right; }");
                out.println("th { background-color: #f2f2f2; font-weight: bold; }");
                out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
                out.println(".status-pending { background-color: #fff3cd; color: #856404; }");
                out.println(".status-completed { background-color: #d4edda; color: #155724; }");
                out.println(".status-shipped { background-color: #cce5ff; color: #004085; }");
                out.println(".price { color: #2e7d32; font-weight: bold; }");
                out.println("</style>");
                out.println("</head><body>");
                
                out.println("<h1>רשימת הזמנות</h1>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>מספר הזמנה</th>");
                out.println("<th>שם משתמש</th>");
                out.println("<th>אימייל</th>");
                out.println("<th>מוצר</th>");
                out.println("<th>כמות</th>");
                out.println("<th>סה\"כ מחיר</th>");
                out.println("<th>תאריך הזמנה</th>");
                out.println("<th>סטטוס</th>");
                out.println("</tr>");
                
                double totalSum = 0;
                int orderCount = 0;
                
                while (rs.next()) {
                    orderCount++;
                    double orderTotal = rs.getDouble("total_price");
                    totalSum += orderTotal;
                    String status = rs.getString("status");
                    
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("order_id") + "</td>");
                    out.println("<td>" + rs.getString("username") + "</td>");
                    out.println("<td>" + rs.getString("email") + "</td>");
                    out.println("<td><strong>" + rs.getString("product_name") + "</strong></td>");
                    out.println("<td>" + rs.getInt("quantity") + "</td>");
                    out.println("<td class='price'>" + String.format("%.2f ₪", orderTotal) + "</td>");
                    out.println("<td>" + rs.getString("order_date") + "</td>");
                    out.println("<td class='status-" + status + "'>" + getStatusInHebrew(status) + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</table>");
                
                // סיכום
                out.println("<hr>");
                out.println("<h3>סיכום:</h3>");
                out.println("<p>סה\"כ הזמנות: <strong>" + orderCount + "</strong></p>");
                out.println("<p>סה\"כ הכנסות: <strong class='price'>" + String.format("%.2f ₪", totalSum) + "</strong></p>");
                
                // קישורים
                out.println("<hr>");
                out.println("<p>");
                out.println("<a href='users'>רשימת משתמשים</a> | ");
                out.println("<a href='products'>רשימת מוצרים</a> | ");
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
    
    private String getStatusInHebrew(String status) {
        switch (status.toLowerCase()) {
            case "pending": return "ממתין";
            case "completed": return "הושלם";
            case "shipped": return "נשלח";
            case "cancelled": return "בוטל";
            default: return status;
        }
    }
}