package com.library.LibraryManagementSystem;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class tst {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPasswordUser = "password"; // Пароль для USER
        String rawPasswordAdmin = "adminpass"; // Пароль для ADMIN

        String encodedPasswordUser = encoder.encode(rawPasswordUser);
        String encodedPasswordAdmin = encoder.encode(rawPasswordAdmin);

        System.out.println("Encoded password for 'user': " + encodedPasswordUser);
        System.out.println("Encoded password for 'admin': " + encodedPasswordAdmin);
    }
}