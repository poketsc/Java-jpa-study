package com.example.javajpa.service;

import com.example.javajpa.domain.Author;
import com.example.javajpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("abc");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생하면 어떻게 될까?");
    }
}
