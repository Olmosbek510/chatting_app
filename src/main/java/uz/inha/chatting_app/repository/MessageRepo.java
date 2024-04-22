package uz.inha.chatting_app.repository;

import jakarta.persistence.TypedQuery;
import uz.inha.chatting_app.entity.BaseEntity;
import uz.inha.chatting_app.entity.Message;
import uz.inha.chatting_app.entity.User;

import java.util.List;
import java.util.UUID;

public class MessageRepo extends BaseRepo<Message, UUID> {
    public static List<Message> findByUserFromUser(User user, User toUser) {
        begin();
        TypedQuery<Message> query = ENTITY_MANAGER.createQuery("from Message where fromUser.id =: fromId and toUser.id =: toId or fromUser.id =: toId and toUser.id =: fromId", Message.class);
        query.setParameter("fromId", user.getId());
        query.setParameter("toId", toUser.getId());
        List<Message> resultList = query.getResultList();
        commit();
        return resultList;
    }
}
