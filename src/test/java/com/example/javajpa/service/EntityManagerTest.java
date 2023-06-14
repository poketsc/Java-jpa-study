package com.example.javajpa.service;

import com.example.javajpa.domain.Member;
import com.example.javajpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManger;
    @Autowired
    private UserRepository userRepository;
    @Test
    void entityManagerTest() {
        System.out.println(entityManger.createQuery("select m from Member m").getResultList());

    }

    @Test
    void cacheFindTest() {
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findByEmail("abc@naver.com"));
        System.out.println(userRepository.findByEmail("abc@naver.com"));
        System.out.println(userRepository.findByEmail("abc@naver.com"));
    }

    @Test
    void cacheFindTest2() {
        Member member = userRepository.findById(1L).get();
        member.setName("abc");

        userRepository.save(member);

        member.setEmail("abc@naver.com");

        userRepository.save(member);
    }
}
