package com.library.LibraryManagementSystem.initializers;

import com.library.LibraryManagementSystem.model.User;
import com.library.LibraryManagementSystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Проверяем, существует ли уже пользователь 'admin'
            if (userRepository.findByUsername("admin").isEmpty()) {
                // Создаем и сохраняем пользователя 'admin'
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpass")); // Шифруем пароль
                Set<String> adminRoles = new HashSet<>();
                adminRoles.add("ADMIN");
                adminRoles.add("USER");
                admin.setRoles(adminRoles);
                userRepository.save(admin);
                System.out.println("Admin user created.");
            }

            // Проверяем, существует ли уже пользователь 'user'
            if (userRepository.findByUsername("user").isEmpty()) {
                // Создаем и сохраняем пользователя 'user'
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("password")); // Шифруем пароль
                Set<String> userRoles = new HashSet<>();
                userRoles.add("USER");
                user.setRoles(userRoles);
                userRepository.save(user);
                System.out.println("Regular user created.");
            }
        };
    }
}