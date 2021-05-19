package com.spring.security.springsecuritydemo.controller;

/*
A controller for login page
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        if (!userRepository.findByEmail(user.getEmail()).isEmpty()) {
            return "redirect:/unsuccessfulauth";
        }

        user.setRole(Role.USER);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/unsuccessfulauth")
    public String unsuccessfulAuth() {
        return "unsuccessfulauth";
    }

}
