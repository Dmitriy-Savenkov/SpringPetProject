package com.spring.security.springsecuritydemo.controller;

/**
 * A controller for login pages
 *
 * @autor Dmitriy Savenkov
 * @version 1.0
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.model.user.Role;
import com.spring.security.springsecuritydemo.model.user.Status;
import com.spring.security.springsecuritydemo.model.user.User;
import com.spring.security.springsecuritydemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//
//    @GetMapping("/login")
//    public String getLoginPage() {
//        return "login";
//    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Model model) {
        if (!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            model.addAttribute("message", "User with this E-mail already exists!");
            return "registration";
        }

        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/login";
    }

}
