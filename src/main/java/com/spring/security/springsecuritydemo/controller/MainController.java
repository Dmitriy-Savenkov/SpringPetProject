package com.spring.security.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница"); // передаем параметр в шаблон
        return "home"; // вызываем HTML шаблон
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Страница про нас"); // передаем параметр в шаблон
        return "about"; // вызываем HTML шаблон
    }

}
