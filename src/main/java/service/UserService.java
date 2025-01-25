package service;

import dao.UserDAO;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;
    private final  BCryptPasswordEncoder passwordEncoder;

    public void saveUser(User user){
        userDAO.save(user);
    }
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public Optional<User> getRegisteredUser(String login, String password) {
        List<User> users = getAllUsers();
        Optional<User> registeredUser = users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .findFirst();
        return registeredUser;
    }

    public String getHashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
