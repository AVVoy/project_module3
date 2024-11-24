package service;

import dao.UserDAO;
import entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public void saveUser(User user){
        userDAO.save(user);
    }
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }
}
