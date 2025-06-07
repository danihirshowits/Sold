<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            color: #333;
        }
        .btn {
            padding: 15px 30px;
            margin: 20px;
            font-size: 18px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border: none;
            border-radius: 8px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<h1>ניהול מערכת</h1>

<form action="addUser.jsp" method="get">
    <button type="submit" class="btn">➕ הוסף משתמש</button>
</form>

<form action="addExam.jsp" method="get">
    <button type="submit" class="btn">📝 הוסף בחינה</button>
</form>
<% 
    String success = request.getParameter("success");
    String error = request.getParameter("error");
%>

<% if (success != null) { %>
    <div style="color: green; font-weight: bold; margin-bottom: 15px;">
        <%= success %>
    </div>
<% } %>

<% if (error != null) { %>
    <div style="color: red; font-weight: bold; margin-bottom: 15px;">
       <%
    String error = request.getParameter("error");
    String success = request.getParameter("success");
%>

<% if (error != null) { %>
    <div style="color: red; font-weight: bold; margin-bottom: 20px;">
        <%= error %>
    </div>
<% } else if (success != null) { %>
    <div style="color: green; font-weight: bold; margin-bottom: 20px;">
        <%= success %>
    </div>
<% } %>

    </div>
<% } %>

</body>
</html>
