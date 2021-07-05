package com.spring.security.springsecuritydemo.model.blog;

/**
 * A class of our entity - Post
 * @autor Dmitriy Savenkov
 * @version 1.0
 */

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
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


    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }
}
