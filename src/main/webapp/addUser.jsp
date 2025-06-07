<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="he">
<head>
    <meta charset="UTF-8">
    <title>הוספת משתמש</title>
    <style>
        body {
            background: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background: white;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
            text-align: right;
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
        }

        select {
            width: 100%;
            padding: 10px;
            border-radius: 6px;
            border: 1px solid #ccc;
            margin-bottom: 15px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px;
            width: 100%;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>הוספת משתמש חדש</h2>

    <%-- הצגת שגיאה או הצלחה --%>
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

    <form action="AddUserServlet" method="post">
    <form action="AddUserServlet" method="post">
        
        <label>שם העובד</label>
        <input type="text" name="name" required>
        <label>שם משתמש:</label>
        <input type="text" name="username" required>

        <label>דוא"ל:</label>
        <input type="email" name="email" required>

        <label>סיסמה:</label>
        <input type="password" name="password" required>

        <label>תפקיד:</label>
        <select name="role" required>
             <option value="admin">אדמין</option>
            <option value="student">רכז</option>
            <option value="admin">עורך לשון</option>
            <option value="admin">עורך מדעי</option>
            <option value="admin">גרפיקאי</option>
           
            
        </select>

        <input type="submit" value="הוסף משתמש">
    </form>
</div>

</body>
</html>

