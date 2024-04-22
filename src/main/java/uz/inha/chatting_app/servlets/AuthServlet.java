package uz.inha.chatting_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.inha.chatting_app.entity.User;
import uz.inha.chatting_app.repository.UserRepo;
import uz.inha.chatting_app.services.AuthService;

import java.io.IOException;

@WebServlet(name = "auth", value = "/user/auth")
public class AuthServlet extends HttpServlet {
    private static final UserRepo userRepo = new UserRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("remember_me");
        HttpSession session = req.getSession();
        if (AuthService.authUser(phone, password)) {
            User user = userRepo.finByPhone(phone);
            if (rememberMe != null) {
                Cookie cookie = new Cookie("currentUser",user.getId().toString());
                cookie.setSecure(false);
                cookie.setPath("/");
                cookie.setMaxAge(60*60);
                resp.addCookie(cookie);
            }
            session.setAttribute("currentUser", user);
            resp.sendRedirect("/mainPage.jsp");
        }else {
            resp.sendRedirect("/index.jsp");
        }
    }
}
