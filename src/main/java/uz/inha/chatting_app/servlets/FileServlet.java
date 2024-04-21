package uz.inha.chatting_app.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uz.inha.chatting_app.entity.Attachment;
import uz.inha.chatting_app.entity.AttachmentContent;
import uz.inha.chatting_app.repository.AttachmentContentRepo;
import uz.inha.chatting_app.repository.AttachmentRepo;
import uz.inha.chatting_app.repository.BaseRepo;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "file", value = "/file")
public class FileServlet extends HttpServlet {
    private final AttachmentContentRepo attachmentContentRepo = new AttachmentContentRepo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("id"));
        if (uuid != null) {
            AttachmentContent attachmentContent = BaseRepo.ENTITY_MANAGER.createQuery("select a from AttachmentContent a where a.attachment.id =: id", AttachmentContent.class).setParameter("id", uuid).getSingleResult();
            resp.getOutputStream().write(attachmentContent.getContent());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part part = req.getPart("photo");
        String fileName = part.getSubmittedFileName();
        System.out.println("Type: " + fileName.substring(fileName.lastIndexOf('.')));
        System.out.println(fileName);
        byte[] bytes = part.getInputStream().readAllBytes();
        AttachmentContent attachmentContent = AttachmentContent.builder()
                .attachment(
                        Attachment.builder()
                                .type(fileName.substring(fileName.lastIndexOf('.')))
                                .build()
                )
                .content(bytes)
                .build();
        attachmentContentRepo.save(attachmentContent);
        HttpSession session = req.getSession();
        session.setAttribute("currentFile", attachmentContent.getAttachment().getId());
        String lastUrl = req.getParameter("lastUrl");
        resp.sendRedirect(lastUrl);
    }
}
