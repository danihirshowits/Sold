<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    //HttpSession session = request.getSession(false); // false = אל תיצור סשן חדש אם אין
    String username = null;
    String role = null;
    Boolean loggedIn = false;

    if (session != null) {
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");
        loggedIn = (Boolean) session.getAttribute("loggedIn");
    }

    if (session == null || loggedIn == null || !loggedIn) {
        // המשתמש לא מחובר, נבצע הפניה לדף ההתחברות
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html lang="he">
<head>
    <meta charset="UTF-8">
    <title>לוח בקרה</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #eaf6fb;
            direction: rtl;
            text-align: center;
            padding-top: 60px;
        }
        .dashboard-box {
            background: #fff;
            margin: auto;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.15);
            width: 350px;
            display: inline-block;
        }
        h2 {
            color: #2196F3;
            margin-bottom: 20px;
        }
        .info {
            font-size: 18px;
            margin-bottom: 15px;
        }
        .logout {
            display: inline-block;
            margin-top: 20px;
            padding: 8px 18px;
            background: #f44336;
            color: #fff;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            cursor: pointer;
        }
        .logout:hover {
            background: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="dashboard-box">
        <h2>שלום <%= username %>!</h2>
        <div class="info">התפקיד שלך: <b><%= role %></b></div>
        <div class="info">ברוך הבא ללוח הבקרה שלך.</div>
        <a class="logout" href="logout.jsp">התנתק</a>
    </div>
</body>
</html>
