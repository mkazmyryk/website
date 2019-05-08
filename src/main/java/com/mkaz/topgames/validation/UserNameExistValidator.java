package com.mkaz.topgames.validation;

import com.mkaz.topgames.entity.User;
import com.mkaz.topgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameExistValidator implements ConstraintValidator<UserNameExistValidation, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userNameValidate(username);
    }

    @Override
    public void initialize(UserNameExistValidation constraintAnnotation) {

    }

    private boolean userNameValidate(String username) {
        User user = userRepository.findByUserNameIgnoreCase(username);
        if (user != null) {
            return false;
        }
        return true;
    }
}
