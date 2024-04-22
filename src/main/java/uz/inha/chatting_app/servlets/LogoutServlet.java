package uz.inha.chatting_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "logout", value = "/user/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("currentUser");
        HttpSession session = req.getSession();
        session.removeAttribute("currentUser");
        session.invalidate();
        for (Cookie cookie : req.getCookies()) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            cookie.setSecure(false);
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/index.jsp");
    }
}
