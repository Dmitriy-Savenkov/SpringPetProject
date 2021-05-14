package com.spring.security.springsecuritydemo.config;

import com.spring.security.springsecuritydemo.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.  annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // говорим, что у нас Security реализовано в методах
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // механизм защиты от csrf угрозы
                .authorizeRequests() // Решение к каким страницам сущность имеет доступ
                .antMatchers("/").permitAll() // Указывает на какие паттерны URL кто имеет доступ
                .anyRequest() // Каждый запрос
                .authenticated()    // должен быть аутентифицирован (проверен, друг или враг)
                .and()
                .formLogin()  // мы хотим использовать кастомный метод авторизации
                .loginPage("/auth/login").permitAll() // ссылка на кастомный метод авторизации
                .defaultSuccessUrl("/auth/success") // ссылка на успешную авторизацию
                .and()
                .logout()   // Просим учесть в конфиге, что сы хотим натсроить LogOut
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))   // LogOut обрабатываем AntPathRequestMatcher с методом Пост
                .invalidateHttpSession(true)     // Инвалидируем (обновление элементов) сессию
                .clearAuthentication(true)      // Очищаем аутентификацию (сущность, которая содержит инфу о юзере)
                .deleteCookies("JSESSIONID")        // Удаляем кукки
                .logoutSuccessUrl("/auth/login");       // Перенаправляем на успешную стр.
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.USER.getAuthorities())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
