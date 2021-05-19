package com.spring.security.springsecuritydemo.config;

/*
Class for indicating settings:
_Authentication
_Login
_Logout
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  // говорим, что у нас Security реализовано в методах
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()   // механизм защиты от csrf угрозы
                .authorizeRequests() // Решение к каким страницам сущность имеет доступ
                .antMatchers("/", "/registration", "/about", "/unsuccessfulauth").permitAll() // Указывает на какие паттерны URL кто имеет доступ
                .anyRequest() // Каждый следующий запрос
                .authenticated()    // должен быть аутентифицирован
                .and()
                .formLogin()  // мы хотим использовать кастомный метод авторизации
                .loginPage("/login").permitAll() // ссылка на кастомный метод авторизации
                .defaultSuccessUrl("/") // ссылка на успешную авторизацию
                .and()
                .logout()   // Просим учесть в конфиге, что сы хотим натсроить LogOut
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))   // LogOut обрабатываем AntPathRequestMatcher с методом Пост
                .invalidateHttpSession(true)     // Инвалидируем (обновление элементов) сессию
                .clearAuthentication(true)      // Очищаем аутентификацию (сущность, которая содержит инфу о юзере)
                .deleteCookies("JSESSIONID")        // Удаляем кукки
                .logoutSuccessUrl("/");       // Перенаправляем на успешную стр.
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    /*
    AuthenticationManager это просто интерфейс, поэтому реализация может быть какой угодно,
    в зависимости от нашего выбора. Реализация по умолчанию в Spring Security называется ProviderManager и вместо того,
    чтобы самостоятельно обрабатывать аутентификационный запрос, он делегирует это списку настроенных
    AuthenticationProvider'ов, каждый из которых в свою очередь запрашивается, может ли он выполнить аутентификацию.
    Каждый провайдер либо сгенерирует исключение, либо вернет полностью заполненный объект Authentication.

    DaoAuthenticationProvider - поддерживаемый провайдеров в этом каркасе.
    Он опирается на UserDetailsService (как DAO) для поиска имя пользователя, пароля и GrantedAuthority.
    Он идентифицирует пользователей просто сравнивая пароль присланный в UsernamePasswordAuthenticationToken с паролем,
    который был загружен UserDetailsService.
     */
    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    // Указываем нашего нового провайдера
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
