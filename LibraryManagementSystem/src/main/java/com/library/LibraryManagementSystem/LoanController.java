package com.library.LibraryManagementSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Loan> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        for (Loan x: loans) {
            System.out.println(x);
        } 
        return loanRepository.findAll();
    }

    @PostMapping
    public String createLoan(@RequestParam Long readerId,
                             @RequestParam Long bookId) {
    
        Reader reader = readerRepository.findById(readerId)
            .orElseThrow(() -> new RuntimeException("Reader not found"));

        Book book =  bookRepository.findById(bookId)
            .orElseThrow(() -> new RuntimeException("Book not found"));

        Loan loan = new Loan(reader, book, LocalDate.now(), null);
        loanRepository.save(loan);

        return "The book has been given to the reader";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable Long id) {
        Loan loan = loanRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loanRepository.save(loan);

        return "The book has been returned";
    }
}
