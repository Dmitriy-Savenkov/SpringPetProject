package com.spring.security.springsecuritydemo.config;

import com.spring.security.springsecuritydemo.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // механизм защиты от csrf угрозы
                .authorizeRequests() // Решение к каким страницам сущность имеет доступ
                .antMatchers("/").permitAll() // Указывает на какие паттерны URL кто имеет доступ
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name()) // на Get запрос все имеют право
                .antMatchers(HttpMethod.POST, "/api/**").hasRole(Role.ADMIN.name()) // на Post запрос только у админа право
                .antMatchers(HttpMethod.DELETE, "/api/**").hasRole(Role.ADMIN.name()) // на DELETE запрос только у админа право
                .anyRequest() // Каждый запрос
                .authenticated()    // должен быть аутентифицирован (проверен, друг или враг)
                .and()
                .httpBasic();  // мы хотим использовать httpBasic Base64
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles(Role.ADMIN.name())
                        .build(),
                User.builder()
                .username("user")
                .password(passwordEncoder().encode("user"))
                .roles(Role.USER.name())
                .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
