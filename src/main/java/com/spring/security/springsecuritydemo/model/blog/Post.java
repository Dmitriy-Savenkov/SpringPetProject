package com.spring.security.springsecuritydemo.model.blog;

/**
 * Class Post - our entity
 */

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@Table(name = "posts")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "anons")
    private String anons;
    @Column(name = "full_text")
    private String fullText;

    public Post(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }
}
