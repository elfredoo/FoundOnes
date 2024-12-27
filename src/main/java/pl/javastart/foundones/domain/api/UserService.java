package pl.javastart.foundones.domain.api;

import org.apache.commons.codec.digest.DigestUtils;
import pl.javastart.foundones.domain.user.User;
import pl.javastart.foundones.domain.user.UserDao;

import java.time.LocalDateTime;

public class UserService {
    private UserDao userDao = new UserDao();

    public void register(UserRegistration userRegistration){
        User user =  UserMapper.map(userRegistration);
        hashPasswordWithSha256(user);
        userDao.save(user);
    }

    private void hashPasswordWithSha256(User user){
        String hashed = DigestUtils.sha256Hex(user.getPassword());
        user.setPassword(hashed);
    }

    private static class UserMapper{
        static User map(UserRegistration userRegistration){
            return new User(
                    userRegistration.getUsername(),
                    userRegistration.getEmail(),
                    LocalDateTime.now(),
                    userRegistration.getPassword());
        }
    }
}
