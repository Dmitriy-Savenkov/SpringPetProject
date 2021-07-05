package com.spring.security.springsecuritydemo.repository;

/**
 * An interface of repository of our entity - Post
 * @autor Dmitriy Savenkov
 * @version 1.0
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
