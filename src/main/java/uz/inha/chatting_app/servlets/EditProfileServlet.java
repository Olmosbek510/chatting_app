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
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "editProfile", value = "/user/editProfile")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String phone = req.getParameter("phone");
        String oldPassword = req.getParameter("old_password");
        String password = req.getParameter("new_password");
        String repeatPassword = req.getParameter("repeat_password");
        HttpSession session = req.getSession();
        Object o = session.getAttribute("currentUser");
        if(o!=null){
            User user = (User) o;
            if(password.equals(repeatPassword) && BCrypt.checkpw(oldPassword, user.getPassword())){
                UserRepo.begin();
                User user1 = UserRepo.ENTITY_MANAGER.find(User.class, user.getId());
                user1.setFirstName(firstname);
                user1.setLastName(lastname);
                Object o1 = session.getAttribute("currentFile");
                if(o1!=null){
                    AttachmentRepo attachmentRepo = new AttachmentRepo();
                    Optional<Attachment> attachmentOptional = attachmentRepo.findById((UUID) o1);
                    attachmentOptional.ifPresent(user1::setPhoto);
                    session.removeAttribute("currentFile");
                }
                user1.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                UserRepo.commit();
                resp.sendRedirect("/index.jsp");
            }else {
                String toUserId = req.getParameter("toUserId");
                if(toUserId!=null){
                    resp.sendRedirect("/profileInfo.jsp?toUserId="+toUserId);
                }else {
                    resp.sendRedirect("/profileInfo.jsp?toUserId="+UUID.randomUUID());
                }
            }
        }else {
            resp.sendRedirect("404");
        }
    }
}
