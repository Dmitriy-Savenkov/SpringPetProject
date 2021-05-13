package com.spring.security.springsecuritydemo.model;

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
