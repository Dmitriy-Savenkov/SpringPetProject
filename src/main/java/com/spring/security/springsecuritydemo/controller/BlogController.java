package com.spring.security.springsecuritydemo.controller;

/*
A controller for blog page
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/blog")
    public String blogMain(Model model) {
        Iterable<Post> posts = postRepository.findAll(); // создаем Итератор по нашим статьям, findAll вытягивает все записи из таблички Post
        model.addAttribute("posts", posts); // Передаем в шаблон "blog-main" все найденные посты по имени "posts", а в качестве значения - данные из итератора
        return "blog-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text); // Создаем статью с переданными параметрами
        postRepository.save(post); // сохраняем в нашу БД созданную статью
        return "redirect:/blog"; // переадресация юзера на страницу /blog
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) { // Если нет такой статьи
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); // Optional - класс, который получает динамич переменные
        List<Post> list = new ArrayList<>(); // т.к. с Optional трудно работать, приводим его к ArrayList
        post.ifPresent(list::add);
        model.addAttribute("post", list); //
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit") // Обработчик для редактирования статей
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {   // @PathVariable - аннотация для получения динамического параметра
        if (!postRepository.existsById(id)) { // Если нет такой статьи
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id); // Optional - класс, который получает динамич переменные
        List<Post> list = new ArrayList<>(); // т.к. с Optional трудно работать, приводим его к ArrayList
        post.ifPresent(list::add);
        model.addAttribute("post", list); //
        return "blog-edit";
    }

    // обработчик при добавлении новой статьи на странице /blog/add
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new); // Создаем статью с переданными параметрами
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(full_text);
        postRepository.save(post); // т.к. это не new Post(), а полученная статья и БД, то .save() не создаст новую статью, а update старую
        return "redirect:/blog"; // переадресация юзера на страницу /blog
    }

    // обработчик при удалении статьи на странице /blog/
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new); // Создаем статью с переданными параметрами
        postRepository.delete(post); // т.к. это не new Post(), а полученная статья и БД, то .save() не создаст новую статью, а update старую
        return "redirect:/blog"; // переадресация юзера на страницу /blog
    }
}
