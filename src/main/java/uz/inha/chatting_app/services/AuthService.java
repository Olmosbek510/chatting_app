package uz.inha.chatting_app.services;

import org.mindrot.jbcrypt.BCrypt;
import uz.inha.chatting_app.entity.User;
import uz.inha.chatting_app.repository.UserRepo;

import java.util.Objects;

public class AuthService {
    private static final UserRepo userRepo = new UserRepo();
    public static boolean authUser(String phone, String password) {
        User user = userRepo.finByPhone(phone);
        if(user==null){
            return false;
        }
        System.out.println("Firstname: "+ user.getFirstName());
        System.out.println("User password: "+ user.getPassword());
        return BCrypt.checkpw(password, user.getPassword());
    }
}
