package com.library.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    
    @Autowired
    private BookRepository repository;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return repository.findAll();
    }

    @PostMapping("/books")
    public String addBook(@RequestBody Book book) {
        repository.save(book);
        return "Book saved";
    }

    @DeleteMapping("/books/{id}")
    public String deleteBook(@PathVariable Long id) {
        repository.deleteById(id);
        return "Book is removed";
    }
}
