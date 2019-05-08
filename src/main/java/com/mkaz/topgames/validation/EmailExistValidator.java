package com.mkaz.topgames.validation;

import com.mkaz.topgames.entity.User;
import com.mkaz.topgames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistValidator implements ConstraintValidator<EmailExistValidation, String> {
    private UserRepository userRepository;

    public EmailExistValidator() {
    }

    @Autowired
    public EmailExistValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return emailExistValide(email);
    }

    @Override
    public void initialize(EmailExistValidation constraintAnnotation) {

    }

    private boolean emailExistValide(String email) {
        if (userRepository == null) {
            return true;
        }
        User user = userRepository.findByEmailIgnoreCase(email);
        return user == null;
    }
}
