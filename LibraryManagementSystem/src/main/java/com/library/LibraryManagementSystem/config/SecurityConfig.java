package com.library.LibraryManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF для простоты (для API без сессий)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/books").permitAll() // Разрешаем доступ к /books без аутентификации (пока для всех методов)
                //.requestMatchers(HttpMethod.GET, "/books").permitAll() // Если хочешь разрешить только GET /books
                //.requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN") // Пример: POST /books только для ADMIN
                .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
            )
            .httpBasic(withDefaults()); // Используем базовую HTTP-аутентификацию

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("adminpass")
            .roles("ADMIN", "USER") // Администратор имеет обе роли
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}