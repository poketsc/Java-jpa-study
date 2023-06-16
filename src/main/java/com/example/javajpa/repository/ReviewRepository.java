package com.example.javajpa.repository;

import com.example.javajpa.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // n+1 문제 해결법
    // (1)
    @Query("select distinct r from Review r join fetch r.comments")
    List<Review> findAllByfetchJoin();

    //(2)
    @EntityGraph(attributePaths = "comments")
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    //(3)
    @EntityGraph(attributePaths = "comments")
    List<Review> findAll();
}
