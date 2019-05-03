package com.mkaz.website.controller;

import com.mkaz.website.entity.Role;
import com.mkaz.website.entity.User;
import com.mkaz.website.repository.RolesRepository;
import com.mkaz.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user) {
        User userDB = userRepository.findUserByUserName(user.getUserName());
        if (userDB != null) {
            return "/registration";
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        Role role = rolesRepository.findByName("ROLE_USER");
        if (role != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        } else {
            role = new Role();
            role.setName("ROLE_USER");
            role.getUsers().add(user);
            user.getRoles().add(role);
        }
        rolesRepository.save(role);
        userRepository.save(user);
        return "redirect:/login";
    }
}
