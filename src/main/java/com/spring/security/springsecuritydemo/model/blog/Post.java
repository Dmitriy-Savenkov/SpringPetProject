package com.spring.security.springsecuritydemo.model.blog;

/*
Post - посты, статьи и тд. Наш блог
 */

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Автоматический инкремент переменной при создании записи в БД
    private Long id;

    private String title, anons, fullText;
    private int views;

    public Post() {
    }

    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }
}
