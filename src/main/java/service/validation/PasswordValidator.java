package service.validation;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {

    public List<String> validate(String password) {
        List<String> errorMessages = new ArrayList<>();

        if(password.length() < 5) {
            errorMessages.add("This password is too short");
        }

        if (!password.contains("@") && !password.contains("#")) {
            errorMessages.add("password must contains @ or #");
        }
        return errorMessages ;
    }
}
