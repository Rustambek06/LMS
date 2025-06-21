package com.library.LibraryManagementSystem;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Reader reader;

    @ManyToOne
    private Book book;

    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan() {}

    public Loan(Reader reader, Book book, LocalDate loanDate, LocalDate returnDate) {
        this.reader = reader;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Long getId() { return id; }
    public Reader getReader() { return reader; }
    public Book getBook() { return book; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setId(Long id) { this.id = id; }
    public void setReader(Reader reader) { this.reader = reader; }
    public void setBook(Book book) { this.book = book; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
