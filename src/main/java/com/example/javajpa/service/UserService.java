package com.example.javajpa.service;

import com.example.javajpa.domain.Member;
import com.example.javajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        Member member = new Member();
        member.setName("abc");
        member.setEmail("abc@naver.com");

        entityManager.persist(member);
//        entityManager.detach(member);

        member.setName("abc2");
        entityManager.merge(member);
//        entityManager.flush();
//        entityManager.clear();

        Member member1 = userRepository.findById(1L).get();
        System.out.println("====================" + member1);

        entityManager.remove(member1);

    }

}
