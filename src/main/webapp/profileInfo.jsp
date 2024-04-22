<%@ page import="java.util.UUID" %>
<%@ page import="uz.inha.chatting_app.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 22/04/24
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .profile-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .profile-header img {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 4px solid #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 10px;
        }

        .profile-name {
            font-size: 24px;
            font-weight: bold;
            color: #333;
        }

        .profile-info {
            margin-bottom: 20px;
            padding: 0 20px;
        }

        .profile-info p {
            margin: 10px 0;
            color: #666;
        }

        .btn-container {
            text-align: center;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            text-decoration: none;
        }

        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%
    UUID toUserId = UUID.fromString(request.getParameter("toUserId"));
    Object o = session.getAttribute("currentUser");
    User user = new User();
    if(o!=null){
        user = (User) o;
    }
%>
<div class="container">
    <div class="profile-header">
        <label>
            <%if (user.getPhoto() == null) {%>
            <img width="50" height="50"
                 src="data:image/png;base64,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"
                 alt="">
            <%} else {%>
            <img id="current-user-photo" width="50" height="50" src="/file?id=<%=user.getPhoto().getId()%>"
                 alt="Profile Photo"
                 style="width: 70px; height: 70px; border-radius: 50%;">
            <%}%>
        </label>
        <div class="profile-name"><%=user.getFirstName()%></div>
    </div>
    <div class="profile-info">
        <p><strong>First Name:</strong> <%=user.getFirstName()%></p>
        <p><strong>Last Name:</strong> <%=user.getLastName()%></p>
        <p><strong>Phone:</strong> <%=user.getPhone()%></p>
    </div>
    <div class="btn-container">
        <a href="editProfile.jsp?toUserId=<%=toUserId%>" class="btn btn-dark text-white">Edit Profile</a>
        <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-danger text-white">Logout</a>
        <a href="mainPage.jsp?toUserId=<%=toUserId%>" class="btn">Back</a>
    </div>
</div>
</body>
</html>
