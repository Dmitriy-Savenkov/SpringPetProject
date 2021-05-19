package com.spring.security.springsecuritydemo.model.blog;

/*
Post - посты, статьи и тд. Наш блог
 */

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Автоматический инкремент переменной при создании записи в БД
    private Long id;

    @Column
    private String title;
    @Column
    private String anons;
    @Column
    private String fullText;

    public Post() {
    }

    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }
}
