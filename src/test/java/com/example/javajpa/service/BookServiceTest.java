package com.example.javajpa.service;

import com.example.javajpa.domain.Book;
import com.example.javajpa.repository.AuthorRepository;
import com.example.javajpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        try{
            bookService.putBookAndAuthor();
        } catch (RuntimeException e) {
            System.out.println(e);
        }

        System.out.println(bookRepository.findAll());
        System.out.println(authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println("----" + bookRepository.findAll());
    }
}