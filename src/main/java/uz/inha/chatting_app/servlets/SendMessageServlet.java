package uz.inha.chatting_app.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.inha.chatting_app.entity.Message;
import uz.inha.chatting_app.entity.User;
import uz.inha.chatting_app.repository.MessageRepo;
import uz.inha.chatting_app.repository.UserRepo;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "sendMessage", value = "/message/send")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepo userRepo = new UserRepo();
        MessageRepo messageRepo = new MessageRepo();
        String messageText = req.getParameter("message");
        UUID fromId = UUID.fromString(req.getParameter("fromUser"));
        UUID toId = UUID.fromString(req.getParameter("toUser"));
        Optional<User> fromOpt = userRepo.findById(fromId);
        Optional<User> toOpt = userRepo.findById(toId);

        Message message = new Message();
        message.setText(messageText);
        if (fromOpt.isPresent() && toOpt.isPresent()) {
            System.out.println("Entering to save message");
            message.setFromUser(fromOpt.get());
            message.setToUser(toOpt.get());
            messageRepo.save(message);
        }
        resp.sendRedirect("/mainPage.jsp?toUserId="+toId);
    }
}
