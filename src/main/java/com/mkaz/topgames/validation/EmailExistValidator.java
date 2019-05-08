package com.mkaz.topgames.validation;

import com.mkaz.topgames.entity.User;
import com.mkaz.topgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistValidator implements ConstraintValidator<EmailExistValidation, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return emailExist(email);
    }

    @Override
    public void initialize(EmailExistValidation constraintAnnotation) {

    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        if (user != null) {
            return false;
        }
        return true;
    }
}
