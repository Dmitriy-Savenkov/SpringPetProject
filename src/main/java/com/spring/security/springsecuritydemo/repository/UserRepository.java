package com.spring.security.springsecuritydemo.repository;

/**
 * UserRepository - our DB connector for User entity
 */

import com.spring.security.springsecuritydemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // Возвращает Юзера по главному идентификатору - Email
}
