package com.mkaz.topgames.validation;

import com.mkaz.topgames.entity.User;
import com.mkaz.topgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameExistValidator implements ConstraintValidator<UserNameExistValidation, String> {
    private UserRepository userRepository;

    public UserNameExistValidator() {
    }

    @Autowired
    public UserNameExistValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userNameValidate(username);
    }

    @Override
    public void initialize(UserNameExistValidation constraintAnnotation) {

    }

    private boolean userNameValidate(String username) {
        if (userRepository == null) {
            return true;
        }
        User user = userRepository.findByUserNameIgnoreCase(username);
        return user == null;
    }
}
