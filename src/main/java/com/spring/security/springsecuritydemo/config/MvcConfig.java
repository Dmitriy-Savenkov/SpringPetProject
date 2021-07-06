package com.spring.security.springsecuritydemo.config;

/**
 * A class which contains configuration of our Web layer
 *
 * @autor Dmitriy Savenkov
 * @version 1.0
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
