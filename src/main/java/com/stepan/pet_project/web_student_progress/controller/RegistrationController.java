package com.stepan.pet_project.web_student_progress.controller;

import com.stepan.pet_project.web_student_progress.entity.security_entity.User;
import com.stepan.pet_project.web_student_progress.service.authority_service.RoleService;
import com.stepan.pet_project.web_student_progress.service.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

/**
 * @author Cupriyanovich Stepan
 * @version 1.0
 */

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUSer(User user, Model model) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User userByName = userService.findByUsername(user.getUsername());

        if (userByName != null) {
            model.addAttribute("message", "User exist!");
            return "registration";
        }

        String password = user.getPassword();
        user.setPassword(encoder.encode(password));

        user.setEnabled(true);
        user.setAuthorities(Collections.singleton(roleService.findByRole("ROLE_STUDENT")));

        userService.save(user);

        return "redirect:/login";
    }
}
