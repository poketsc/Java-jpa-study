package com.example.javajpa.repository;

import com.example.javajpa.domain.*;
import com.example.javajpa.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Test
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("abc");

//        bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("회사");

//        publisherRepository.save(publisher);

        book.setPublisher(publisher);
        bookRepository.save(book);

//        똑같지만 가독성을 위해서 getBooks().add(book)은 하지 않는다.
//        publisher.getBooks().add(book);
//        publisher.addBook(book);
//        publisherRepository.save(publisher);

        System.out.println(bookRepository.findAll());
        System.out.println(publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("회사2");

        bookRepository.save(book1);

        System.out.println(publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println(bookRepository.findAll());
        System.out.println(publisherRepository.findAll());
        System.out.println(bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void bookRemoveCascadeTest() {
        System.out.println(bookRepository.findAll());
        System.out.println(publisherRepository.findAll());
    }

    @Test
    void queryTest() {
        System.out.println(bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                "abc",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)
        ));
    }

    @Test
    void nativeQueryTest() {
        bookRepository.findAll().forEach(System.out::println);
        bookRepository.findAllCustom().forEach(System.out::println);

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setCategory("abc");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println(bookRepository.updateCategories());
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

    @Test
    void converterTest() {
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("abc");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
    }
}
