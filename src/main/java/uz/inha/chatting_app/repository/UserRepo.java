package uz.inha.chatting_app.repository;

import uz.inha.chatting_app.entity.User;

import java.util.UUID;

public class UserRepo extends BaseRepo<User, UUID>{

    public static boolean isExists(String phone) {
        begin();
        User user = ENTITY_MANAGER.createQuery("select u from User u where u.phone =: phone", User.class)
                .setParameter("phone", phone).getSingleResultOrNull();
        commit();
        return user != null;
    }

    public User finByPhone(String phone) {
        begin();
        User resultOrNull = ENTITY_MANAGER.createQuery("select u from User u where u.phone=:phone", User.class).setParameter("phone", phone).getSingleResultOrNull();
        commit();
        return resultOrNull;
    }
}
