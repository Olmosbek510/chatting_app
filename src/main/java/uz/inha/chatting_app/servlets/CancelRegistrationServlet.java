package uz.inha.chatting_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.inha.chatting_app.repository.BaseRepo;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "cancelRegister", value = "/registration/cancel")
public class CancelRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object o = session.getAttribute("currentFile");
        if (o!=null) {
            UUID uuid = (UUID) o;
            BaseRepo.begin();
            BaseRepo.ENTITY_MANAGER.createQuery("delete from AttachmentContent where attachment.id =: id").setParameter("id",uuid).executeUpdate();
            BaseRepo.commit();
            session.removeAttribute("currentFile");
        }
    }
}
