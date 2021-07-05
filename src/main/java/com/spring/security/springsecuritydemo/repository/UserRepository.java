package com.spring.security.springsecuritydemo.repository;

/**
 * An interface of repository of our entity - User
 * @autor Dmitriy Savenkov
 * @version 1.0
 */


import com.spring.security.springsecuritydemo.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // Возвращает Юзера по главному идентификатору - Email
}
