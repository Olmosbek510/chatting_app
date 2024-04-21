<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://c8.alamy.com/comp/KMK3AB/digital-composite-of-chat-bubble-app-icons-and-dark-background-KMK3AB.jpg'); /* Replace with your background image URL */
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            text-align: center;
            background-color: rgba(255, 255, 255, 0.8); /* Semi-transparent white background */
            padding: 20px;
            border-radius: 10px;
        }
        h1 {
            color: #333;
        }
        .btn {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border: none;
            border-radius: 5px;
            margin: 10px;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome</h1>
    <a href="login.jsp"><button class="btn">Login</button></a>
    <a href="register.jsp"><button class="btn">Register</button></a>
</div>
</body>
</html>