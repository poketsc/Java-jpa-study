package com.example.javajpa.service;

import com.example.javajpa.domain.Author;
import com.example.javajpa.domain.Book;
import com.example.javajpa.repository.AuthorRepository;
import com.example.javajpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

//    @Transactional(rollbackFor = Exception.class)
//    public void putBookAndAuthor() {
//        Book book = new Book();
//        book.setName("abc");
//
//        bookRepository.save(book);
//
//        Author author = new Author();
//        author.setName("bsc");
//
//        authorRepository.save(author);
//
////        이렇게 runtimeexception을 발생 시키면 commit이 되지 않는다.
//        throw new RuntimeException("오류가 나서 commit 발생 안했습니다.");
//
////        하지만 exception 처리를 하면 커밋이 발생 그래서 try catch 문에서 롤백 처리 해야된다.
////        그게 아니면 rollbackFor 를 사용해서 롤백 시킴
////        throw new Exception("오류 발생");
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("abc");

        bookRepository.save(book);

        try {
            authorService.putAuthor();
        }catch (RuntimeException e) {
        }

        throw new RuntimeException("오류가 발생하면 어떻게 될까?");
    }


//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
//    public void get(Long id) {
//        System.out.println(bookRepository.findById(id));
//        System.out.println(bookRepository.findAll());
//
//        System.out.println(bookRepository.findById(id));
//        System.out.println(bookRepository.findAll());
//
//        Book book = bookRepository.findById(id).get();
//        book.setName("change");
//        bookRepository.save(book);
//    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id) {
        System.out.println(bookRepository.findById(id));
        System.out.println(bookRepository.findAll());

        System.out.println(bookRepository.findById(id));
        System.out.println(bookRepository.findAll());

        Book book = bookRepository.findById(id).get();
        book.setName("change");
        bookRepository.save(book);
    }
    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }
}
