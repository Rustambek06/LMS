package com.library.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    
    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @PostMapping
    public String createReader(@RequestBody Reader reader) {
        readerRepository.save(reader);

        return "The reader is registered";
    }
}
