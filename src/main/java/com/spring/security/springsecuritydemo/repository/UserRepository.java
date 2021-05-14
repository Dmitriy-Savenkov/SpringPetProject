package com.spring.security.springsecuritydemo.repository;

/*
An interface which communicates with DB.
 */

import com.spring.security.springsecuritydemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // Возвращает Юзера по главному идентификатору - Email
}
