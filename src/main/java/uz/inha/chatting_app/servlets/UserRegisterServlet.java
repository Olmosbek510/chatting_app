package uz.inha.chatting_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import uz.inha.chatting_app.entity.Attachment;
import uz.inha.chatting_app.entity.User;
import uz.inha.chatting_app.repository.AttachmentRepo;
import uz.inha.chatting_app.repository.UserRepo;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "register", value = "/user/registration")
public class UserRegisterServlet extends HttpServlet {
    private final AttachmentRepo attachmentRepo =  new AttachmentRepo();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        System.out.println(password);
        String rep_password = req.getParameter("repeat-password");
        HttpSession session = req.getSession();
        if(Objects.equals(password,rep_password) && !UserRepo.isExists(phone)){
            User user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setPhone(phone);
            user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
            if(session.getAttribute("currentFile")!=null){
                Optional<Attachment> byId = attachmentRepo.findById((UUID) session.getAttribute("currentFile"));
                byId.ifPresent(user::setPhoto);
            }
            UserRepo userRepo = new UserRepo();
            userRepo.save(user);
        }
        session.removeAttribute("currentFile");
        resp.sendRedirect("/index.jsp");
    }
}
