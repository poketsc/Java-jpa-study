package com.example.javajpa.domain.listener;

import com.example.javajpa.domain.Member;
import com.example.javajpa.domain.UserHistory;
import com.example.javajpa.repository.UserHistoryRepository;
import com.example.javajpa.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        Member member = (Member) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setName(member.getName());
        userHistory.setEmail(member.getEmail());
        userHistory.setMember(member);
        userHistory.setHomeAddress(member.getHomeAddress());
        userHistory.setCompanyAddress(member.getCompanyAddress());

        userHistoryRepository.save(userHistory);

    }
}
