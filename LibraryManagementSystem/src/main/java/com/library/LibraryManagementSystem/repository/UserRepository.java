package com.library.LibraryManagementSystem.repository; // Обнови пакет

import com.library.LibraryManagementSystem.model.User; // Импортируем сущность User
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Метод для поиска пользователя по имени
}