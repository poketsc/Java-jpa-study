package com.example.javajpa.repository;

import com.example.javajpa.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setAuthorId(1L);
        book.setName("abcd");
        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        givenBookAndReview();

        List<UserHistory> members = userRepository.findByEmail("asdf@naver.com").getUserHistories();
        System.out.println(members);

//        System.out.println(member.getReviews());
//        System.out.println(member.getReviews().get(0).getBook());
//        System.out.println(member.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview() {
        givenReview(givenMember(), givenBook(givenPublisher()));
    }

    private Member givenMember() {
        Member member = new Member();
        member.setEmail("asdf@naver.com");
        member.setGender(Gender.MALE);
        member.setName("asdf");
        return userRepository.save(member);

    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("최고");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("bsc");

        return publisherRepository.save(publisher);
    }

    private void givenReview(Member member, Book book){
        Review review = new Review();
        review.setTitle("abc");
        review.setContent("최고에요");
        review.setScore(5.0f);
        review.setMember(member);
        review.setBook(book);

        reviewRepository.save(review);
    }
}
