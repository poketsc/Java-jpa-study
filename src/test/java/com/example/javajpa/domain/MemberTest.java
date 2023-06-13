package com.example.javajpa.domain;

import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    void test() {
        Member member = new Member();
        member.setEmail("123");
        member.setName("abc");

        System.out.println(member);
    }

}