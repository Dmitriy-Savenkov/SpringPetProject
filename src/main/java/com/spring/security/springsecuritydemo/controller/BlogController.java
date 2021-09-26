package com.spring.security.springsecuritydemo.controller;

/**
 * Controller for Blog entities
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;


    /**
     * Getting list of posts
     *
     * @param model
     * @return
     */
    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    /**
     * Getting a page for adding a post
     *
     * @return
     */
    @GetMapping("/blog/add")
    public String blogAdd() {
        return "blog-add";
    }

    /**
     * Adding a post
     *
     * @param title
     * @param anons
     * @param fullText
     * @return
     */
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String fullText) {
        Post post = new Post(title, anons, fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /**
     * Getting full information of indicated post
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        List<Post> list = new ArrayList<>();
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "blog-details";
    }


    /**
     * Getting a page for editing a post
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) { // Если нет такой статьи
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        List<Post> list = new ArrayList<>();
        post.ifPresent(list::add);
        model.addAttribute("post", list);
        return "blog-edit";
    }


    /**
     * Editing a post
     *
     * @param id
     * @param title
     * @param anons
     * @param fullText
     * @return
     */
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String title,
                                 @RequestParam String anons,
                                 @RequestParam String fullText) {

        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }


    /**
     * Delete a post
     *
     * @param id
     * @return
     */
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id) {

        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        postRepository.delete(post);
        return "redirect:/blog";
    }
}
