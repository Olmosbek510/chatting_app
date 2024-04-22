<%@ page import="uz.inha.chatting_app.repository.UserRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="uz.inha.chatting_app.entity.User" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.hibernate.Session" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-image: url('https://images.unsplash.com/photo-1530533718754-001d2668365a?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'); /* Replace with your background image URL */
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
<%
    final UserRepo userRepo = new UserRepo();
    Cookie[] cookies = request.getCookies();
    if(cookies!=null){
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getValue()!=null && cookie.getName().equals("currentUser")){
                Optional<User> byId = userRepo.findById(UUID.fromString(cookie.getValue()));
                if(byId.isPresent()){
                    session.setAttribute("currentUser", byId.get());
                    System.out.println("current user is: " + byId.get().getFirstName());
                    response.sendRedirect("/mainPage.jsp");
                }
            }
        }
    }
%>
<div class="container">
    <h1>Welcome</h1>
    <a href="login.jsp"><button class="btn">Login</button></a>
    <a href="register.jsp"><button class="btn">Register</button></a>
</div>
</body>
</html>