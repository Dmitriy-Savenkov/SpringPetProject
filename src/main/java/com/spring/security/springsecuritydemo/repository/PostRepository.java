package com.spring.security.springsecuritydemo.repository;

/**
 * PostRepository - our DB connector for Post entity
 */

import com.spring.security.springsecuritydemo.model.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
