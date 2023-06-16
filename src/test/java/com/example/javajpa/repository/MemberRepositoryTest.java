package com.example.javajpa.repository;

import com.example.javajpa.domain.Address;
import com.example.javajpa.domain.Gender;
import com.example.javajpa.domain.Member;
import com.example.javajpa.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void crud() {
        Member member1 = new Member();
        member1.setName("abc");
        member1.setEmail("abc@naver.com");
        Member member2 = new Member();
        member2.setName("abc2");
        member2.setEmail("abc2@naver.com");

        userRepository.saveAll(Lists.newArrayList(member1, member2));

        List<Member> members = userRepository.findAll();

        members.forEach(System.out::println);

    }

    @Test
    void select() {
        Member member1 = new Member();
        member1.setName("abc");
        member1.setEmail("abc@naver.com");
        member1.setCreatedAt(LocalDateTime.now());
        member1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(member1);
//        System.out.println(userRepository.findByEmail("abc@naver.com"));
//        System.out.println(userRepository.findByEmailAndName("abc@naver.com", "abc"));
//        System.out.println(userRepository.findByEmailOrName("abc@naver.com", "abc"));
//        System.out.println(userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1)));
//        System.out.println(userRepository.findByIdAfter(1L));
//        System.out.println(userRepository.findByAddressIsNotEmpty());
//        System.out.println(userRepository.findByNameIn(Lists.newArrayList("abc")));


    }

    @Test
    void pagingAndSortingTest() {
        Member member1 = new Member();
        member1.setName("abc");
        member1.setEmail("abc@naver.com");
        member1.setCreatedAt(LocalDateTime.now());
        member1.setUpdatedAt(LocalDateTime.now());
        userRepository.save(member1);

        Member member2 = new Member();
        member2.setName("abc");
        member2.setEmail("abc2@naver.com");
        member2.setCreatedAt(LocalDateTime.now());
        member2.setUpdatedAt(LocalDateTime.now());
        userRepository.save(member2);
//        System.out.println(userRepository.findAll());
//        System.out.println(userRepository.findTopByNameOrderByIdDesc("abc"));
//        System.out.println(userRepository.findFirstByName("abc", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println(userRepository.findByName("abc", PageRequest.of(0,2, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    @Test
    void insertAndUpdateTest() {
        Member member = new Member();

        member.setName("abc");
        member.setEmail("abc@naver.com");
        userRepository.save(member);

        Member member2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        member2.setName("abc2");

        userRepository.save(member2);

    }

    @Test
    void enumTest() {
        Member member = new Member();

        member.setName("abc");
        member.setEmail("abc@naver.com");
        userRepository.save(member);
        Member member1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setGender(Gender.MALE);
        userRepository.save(member1);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender"));
    }

    @Test
    void listenerTest() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc@naver.com");

        userRepository.save(member);

        Member member1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        member1.setGender(Gender.MALE);
        userRepository.save(member1);

        userRepository.deleteById(1L);

    }

    @Test
    void prePersistTest(){
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc@naver.com");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUpdatedAt(LocalDateTime.now());

        userRepository.save(member);

        System.out.println(userRepository.findByEmail("abc@naver.com"));
    }

    @Test
    void userHistoryTest() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc@naver.com");
//        member.setCreatedAt(LocalDateTime.now());
//        member.setUpdatedAt(LocalDateTime.now());

        userRepository.save(member);

        member.setName("abcde");
        userRepository.save(member);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc@naver.com");
        member.setGender(Gender.MALE);

        userRepository.save(member);

        member.setName("abc2");
        userRepository.save(member);

        member.setEmail("abc2@naver.com");
        userRepository.save(member);

//        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByMemberId(
//                userRepository.findByEmail("abc2@naver.com").getId()
//        );

        List<UserHistory> result = userRepository.findByEmail("abc2@naver.com").getUserHistories();


        result.forEach(System.out::println);

        System.out.println(userHistoryRepository.findAll().get(0).getMember());

    }

    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);

        Member member = new Member();
        member.setName("abc");
        member.setHomeAddress(new Address("서울시", "강남구", "강남대로", "12345"));
        member.setCompanyAddress(new Address("서울시", "성동구", "강남대로", "23456"));

        userRepository.save(member);

        Member member1 = new Member();
        member1.setName("abc2");
        member1.setHomeAddress(null);
        member1.setCompanyAddress(null);

        userRepository.save(member1);

        Member member2 = new Member();
        member2.setName("abc3");
        member2.setHomeAddress(new Address());
        member2.setCompanyAddress(new Address());

        userRepository.save(member2);
//        findAll() 로 찾을떄와 findAllRawRecord() 처럼 raw query를 날릴때 print 해보면 Address 객체에 대한 정보가 다른 모습으로 출력이 되는데 이유는 영속성 컨텍스트 때문이다.
//        entityManager.clear() 를 하면 똑같이 나온다.
//        entityManager.clear();
        userRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a->System.out.println(a.values()));

        assertAll(
                () -> assertThat(userRepository.findById(2L).get().getHomeAddress()).isNull(),
                () -> assertThat(userRepository.findById(3L).get().getHomeAddress()).isInstanceOf(Address.class)
        );
    }
}