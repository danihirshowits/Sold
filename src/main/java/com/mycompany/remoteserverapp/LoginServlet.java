package com.mycompany.remoteserverapp;
import com.mycompany.remoteserverapp.User;
import com.mycompany.remoteserverapp.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
       User user = userDAO.authenticateUser(username, password);
        
        if (user != null) {
            // המשתמש עבר אימות בהצלחה
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());
            session.setAttribute("loggedIn", true);
            if ("ADMIN".equals(session.getAttribute("role"))) {
                 response.sendRedirect("admin.jsp");
            } else {
                response.sendRedirect("dashboard.jsp");
            }
        } else {
            // כישלון באימות
            request.setAttribute("error", "שם משתמש או סיסמה שגויים!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            response.setContentType("text/plain");
        response.getWriter().println("Hello, this is the response!");
            

          //  response.sendRedirect("wrong.html");
       }
    }
}