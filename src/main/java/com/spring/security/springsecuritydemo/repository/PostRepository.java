package com.spring.security.springsecuritydemo.repository;

import com.spring.security.springsecuritydemo.model.blog.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
