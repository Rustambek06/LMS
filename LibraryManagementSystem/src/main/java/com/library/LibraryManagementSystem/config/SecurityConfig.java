package com.library.LibraryManagementSystem.config;

import com.library.LibraryManagementSystem.service.CustomUserDetailsService; // Импортируем наш UserDetailsService
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider; // Для использования UserDetailsService
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // Для кодирования паролей
import org.springframework.security.crypto.password.PasswordEncoder; // Для интерфейса PasswordEncoder
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService; // Внедряем наш сервис

    // Используем конструктор для внедрения зависимости
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/books").permitAll()
                .requestMatchers(HttpMethod.POST, "/books").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .requestMatchers("/readers/**").hasRole("ADMIN")
                .requestMatchers("/loans/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());

        return http.build();
    }

    @Bean // Новый бин для PasswordEncoder
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Настраиваем DaoAuthenticationProvider для использования нашего UserDetailsService и PasswordEncoder
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService); // Указываем наш UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Указываем наш PasswordEncoder
        return authProvider;
    }

    // Удаляем старый In-Memory UserDetailsService, так как теперь используем базу данных
    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withDefaultPasswordEncoder()
    //         .username("user")
    //         .password("password")
    //         .roles("USER")
    //         .build();
    //
    //     UserDetails admin = User.withDefaultPasswordEncoder()
    //         .username("admin")
    //         .password("adminpass")
    //         .roles("ADMIN", "USER")
    //         .build();
    //
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
}