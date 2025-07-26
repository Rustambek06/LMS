package com.library.LibraryManagementSystem.service; // Обнови пакет

import com.library.LibraryManagementSystem.model.User;
import com.library.LibraryManagementSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.Collection;
import java.util.stream.Collectors;

@Service // Помечаем как компонент сервисного слоя Spring
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Ищем пользователя в базе данных по имени пользователя
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Преобразуем нашу сущность User в UserDetails, которую ожидает Spring Security
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    // Вспомогательный метод для преобразования строковых ролей в GrantedAuthority
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<String> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Spring Security ожидает префикс "ROLE_"
                .collect(Collectors.toList());
    }
}