package com.example.javajpa.repository;

import com.example.javajpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
//    이렇게 소프트딜리트 메서드를 만들면 모든 레퍼지토리에서 다 만들어야한다.
//    그래서 엔티티 클래스에서 처리하는게 좋다. @Where()
    List<Book> findAllByDeletedFalse();
}
