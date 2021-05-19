package com.spring.security.springsecuritydemo.repository;

import com.spring.security.springsecuritydemo.model.blog.Post;
import com.spring.security.springsecuritydemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
