<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="he">
<head>
    <meta charset="UTF-8">
    <title>התחברות</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            height: 100vh;
            justify-content: center;
            align-items: center;
            direction: rtl;
        }

        .login-box {
            background-color: white;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
            width: 300px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-top: 10px;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            margin-top: 20px;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
        
        .error-message {
            color: red;
            margin-top: 15px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-box">
        <h2>התחברות</h2>
        <form action="LoginServlet" method="post">
            <label for="username">שם משתמש:</label>
            <input type="text" name="username" required>

            <label for="password">סיסמה:</label>
            <input type="password" name="password" required>

            <input type="submit" value="התחבר">
        </form>
        
        <%-- הדפסת הודעת שגיאה אם קיימת --%>
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="error-message"><%= error %></div>
        <%
            }
        %>
    </div>
</body>
</html>
