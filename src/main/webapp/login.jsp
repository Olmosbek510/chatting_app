<%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 21/04/24
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://images.unsplash.com/photo-1710781944947-7cd4a381499f?q=80&w=2832&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); /* Replace with your background image URL */
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
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Box shadow for depth */
        }

        h2 {
            margin-bottom: 20px;
            color: #333;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
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
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/user/auth" method="post">
        <div class="form-group">
            <label>
                <input name="phone" type="tel" placeholder="Phone Number" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input name="password" type="password" placeholder="Password" required>
            </label>
        </div>
        <div class="form-group">
            <label>
                <input name="remember_me" type="checkbox" value="remember_me">Remember me
            </label>
        </div>
        <div class="button-group">
            <button type="submit" class="btn">Login</button>
            <a href="index.jsp" class="btn btn-cancel">Cancel</a>
        </div>
    </form>

</div>
