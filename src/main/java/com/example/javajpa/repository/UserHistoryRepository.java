package com.example.javajpa.repository;

import com.example.javajpa.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByMemberId(Long memberId);
}
