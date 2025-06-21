package com.library.LibraryManagementSystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a Spring configuration class
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // This mapping applies CORS rules to all endpoints under "/api/**"
        registry.addMapping("/books/**")
                .allowedOrigins("http://localhost:5173", "http://your-frontend-domain.com") // Replace with your actual frontend URLs
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
                .allowedHeaders("*") // Allows all headers in the request
                .allowCredentials(true) // Allows sending cookies, authorization headers, etc.
                .maxAge(3600); // How long the CORS pre-flight response can be cached (in seconds)
    }
}