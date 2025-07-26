package com.library.LibraryManagementSystem.model; // Обнови пакет

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "users") // Название таблицы в базе данных
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) // Имя пользователя должно быть уникальным и не null
    private String username;

    @Column(nullable = false)
    private String password;

    // Используем String для простоты, в реальном проекте лучше использовать отдельную сущность Role
    @ElementCollection(fetch = FetchType.EAGER) // Загружаем роли вместе с пользователем
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    private Set<String> roles = new HashSet<>();

    public User() {}

    public User(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void addRole(String role) {
        this.roles.add(role);
    }
}