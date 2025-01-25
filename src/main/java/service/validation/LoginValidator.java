package service.validation;

import entity.User;
import lombok.RequiredArgsConstructor;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class LoginValidator {

    private final UserService userService;

    public List<String> validate(String login) {
        List<String> errorMessages = new ArrayList<>();
        boolean isExist = userService.getAllUsers().stream().map(User::getLogin).anyMatch(s -> s.equals(login));
        if (isExist) {
            errorMessages.add("This login already is occupied");
        }

        if(login.length() < 5) {
            errorMessages.add("This login is too short");
        }

        if (login.contains(" ")) {
            errorMessages.add("Login must not contains whitespace");
        }
        return errorMessages ;
    }
}
