<%@ page import="uz.inha.chatting_app.repository.UserRepo" %>
<%@ page import="uz.inha.chatting_app.entity.User" %>
<%@ page import="uz.inha.chatting_app.repository.MessageRepo" %>
<%@ page import="uz.inha.chatting_app.entity.Message" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: orazboyevolmosbek
  Date: 22/04/24
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main page</title>
    <link href="./static/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        nav {
            background-color: #333;
            color: #fff;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .user-list {
            width: 200px;
            background-color: #f4f4f4;
            padding: 20px;
            height: calc(100vh - 60px);
            overflow-y: auto;
            float: left;
        }

        .user-table {
            width: 100%;
            border-collapse: collapse;
        }

        .user-table th,
        .user-table td {
            padding: 10px;
            border-bottom: 1px solid #ccc;
            text-align: left;
        }

        .user-table th {
            background-color: #333;
            color: #fff;
        }

        .chat-messages {
            flex: 1;
            padding: 20px;
            height: calc(100vh - 60px);
            overflow-y: auto;
        }

        .message-container {
            background-color: #f9f9f9;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 10px;
        }

        .message-container:last-child {
            margin-bottom: 0;
        }

        .message-text {
            margin: 0;
        }

        .message-time {
            font-size: 12px;
            color: #666;
        }

        .message-sent {
            background-color: #DCF8C6;
            align-self: flex-end;
        }

        .message-received {
            background-color: #EAEAEA;
        }

        .message-input {
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            bottom: 0;
            right: 0;
            width: calc(100% - 220px);
            background-color: #f4f4f4;
            padding: 10px 20px;
        }

        .message-input input[type="text"] {
            flex: 1;
            padding: 8px;
            border: none;
            border-radius: 5px;
            margin-right: 10px;
        }

        .message-input button {
            padding: 8px 20px;
            border: none;
            border-radius: 5px;
            background-color: #333;
            color: #fff;
            cursor: pointer;
        }

        input[type="button"],
        input[type="submit"],
        input[type="reset"] {
            -webkit-appearance: button;
            -moz-appearance: button;
            cursor: pointer;
            outline: none;
            border: none;
            text-decoration: none;
            font: inherit;
            color: inherit;
            background-color: transparent;
            padding: 0;
            margin: 0;
            text-align: inherit;
        }
    </style>
</head>
<body>
<%
    Object o = session.getAttribute("currentUser");
    String toUserId = request.getParameter("toUserId");
    UUID toUserID = UUID.randomUUID();
    if (toUserId != null) {
        toUserID = UUID.fromString(toUserId);
    }
    UserRepo userRepo = new UserRepo();
    User user = null;
    if (o != null) {
        user = (User) o;
    }
    List<Message> messages = new ArrayList<>();
    if (toUserId != null && user != null) {
        Optional<User> toUser = userRepo.findById(toUserID);
        if (toUser.isPresent()) {
            messages = MessageRepo.findByUserFromUser(user, toUser.get());
        }
    }
    assert user != null;
    List<User> otherUsers = userRepo.findAll();
%>
<nav>
    <div>
        <!-- Replace "John Doe" with the current user's name -->
        <!-- Replace "profile.jpg" with the path to the current user's profile photo -->
        <label>
            <%if (user.getPhoto() == null) {%>
            <img width="50" height="50"
                 src="data:image/png;base64,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"
                 alt="">
            <%} else {%>
            <img id="current-user-photo" width="50" height="50" src="/file?id=<%=user.getPhoto().getId()%>"
                 alt="Profile Photo"
                 style="width: 50px; height: 50px; border-radius: 50%;">
            <%}%>
        </label>
        <a style="color: white" href="profileInfo.jsp?toUserId=<%=toUserID%>"
           id="current-user-name"><%=user.getFirstName()%>
        </a>
    </div>
</nav>
<div class="user-list">
    <table class="user-table">
        <thead>
        <tr>
            <th>Users</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (User otherUser : otherUsers) {
                if (!Objects.equals(user.getId(), otherUser.getId())) {
        %>
        <tr class="user">
            <td>
                <%if (toUserId!=null && otherUser.getId().equals(UUID.fromString(toUserId))) {%>
                <a style="color: blue" href="mainPage.jsp?toUserId=<%=otherUser.getId()%>">
                    <%=otherUser.getFirstName()%>
                </a>
                <%}else {%>
                <a style="color: black" href="mainPage.jsp?toUserId=<%=otherUser.getId()%>">
                    <%=otherUser.getFirstName()%>
                </a>
                <%} %>
                <label>
                    <%if (otherUser.getPhoto() == null) {%>
                    <img width="50" height="50"
                         src="data:image/png;base64,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"
                         alt="">
                    <%} else {%>
                    <img id="current-user-photo" width="50" height="50" src="/file?id=<%=otherUser.getPhoto().getId()%>"
                         alt="Profile Photo"
                         style="width: 50px; height: 50px; border-radius: 50%;">
                    <%}%>
                </label></td>
        </tr>
        <%}%>
        <%} %>
        </tbody>
    </table>
</div>

<div class="chat-messages">
    <!-- Replace "You" with the current user's name -->
    <%
        for (Message message : messages) {
            if (message.getFromUser().getId().equals(user.getId())) {
    %>
    <div class="message-container message-sent">
        <p class="message-text"><%=message.getText()%>
        </p>
        <span class="message-time"><%=message.getDateTime()%></span>
    </div>
    <%} else {%>
    <div class="message-container message-received">
        <p class="message-text"><%=message.getText()%>
        </p>
        <span class="message-time"><%=message.getDateTime()%></span>
    </div>
    <%}%>
    <%} %>
</div>

<form action="${pageContext.request.contextPath}/message/send" method="post" class="needs-validation" novalidate>
    <div class="message-input">
        <label>
            <input name="message" type="text" class="form-control" placeholder="Type your message..." required>
            <div class="invalid-feedback">Please enter a message.</div>
        </label>
        <input name="fromUser" type="hidden" value="<%=user.getId()%>">
        <input name="toUser" type="hidden" value="<%=toUserId%>">
        <button type="submit" class="btn btn-primary" id="send-button">Send</button>
    </div>
</form>
</body>
</html>
