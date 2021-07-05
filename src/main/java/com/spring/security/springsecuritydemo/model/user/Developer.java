package com.spring.security.springsecuritydemo.model.user;

/**
 * A class which indicates users of our site
 * @autor Dmitriy Savenkov
 * @version 1.0
 */


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;
}
