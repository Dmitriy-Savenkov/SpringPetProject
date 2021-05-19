package com.spring.security.springsecuritydemo.controller;

/*
A controller for blog page
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.repository.PostRepository;
import com.spring.security.springsecuritydemo.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }


    @GetMapping("/blog/add")
    @PreAuthorize("hasAuthority('developers:read')")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    @PreAuthorize("hasAuthority('developers:change')")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fullText) {
        Post post = new Post(title, anons, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) { // Если нет такой статьи
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        List<Post> list = new ArrayList<>(); // т.к. с Optional трудно работать, приводим его к ArrayList
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "blog-details";
    }


    @GetMapping("/blog/{id}/edit")
    @PreAuthorize("hasAuthority('developers:change')")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) { // Если нет такой статьи
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        List<Post> list = new ArrayList<>(); // т.к. с Optional трудно работать, приводим его к ArrayList
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "blog-edit";
    }

    // Редактирование статьи
    @PostMapping("/blog/{id}/edit")
    @PreAuthorize("hasAuthority('developers:change')")
    public String blogPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String fullText) {
        // Редактируем статью с переданными параметрами
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    // Удаление статьи
    @PostMapping("/blog/{id}/remove")
    @PreAuthorize("hasAuthority('developers:change')")
    public String blogPostDelete(@PathVariable(value = "id") long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
