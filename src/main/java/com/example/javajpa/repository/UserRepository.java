package com.example.javajpa.repository;

import com.example.javajpa.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);

    Member findByEmail(String email);
    Member getByEmail(String email);
    Member readByEmail(String email);
    Member queryByEmail(String email);
    Member searchByEmail(String email);
    Member streamByEmail(String email);
    Member findMemberByEmail(String email);

//    Something이 중요하지 않음 (여기에 들어가는 이름은 중요하지 않음)
    Member findSomethingByEmail(String email);

    Member findFirst1ByName(String name);

    Member findTop1ByName(String name);

    List<Member> findByEmailAndName(String email, String name);

    List<Member> findByEmailOrName(String email, String name);

    List<Member> findByCreatedAtAfter(LocalDateTime yesterday);

//    작동은 하지만 가독성을 위해서 after, before는 날짜에만 사용하기
    List<Member> findByIdAfter(Long id);

    List<Member> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<Member> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);
    List<Member> findByCreatedAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow);

    List<Member> findByIdIsNotNull();
//    List<Member> findByAddressIsNotEmpty();

    List<Member> findByNameIn(List<String> names);

    List<Member> findByNameStartingWith(String name);
    List<Member> findByNameEndingWith(String name);
    List<Member> findByNameContains(String name);

    List<Member> findTopByNameOrderByIdDesc(String name);

    List<Member> findFirstByNameOrderByIdDescEmailAscName(String name);

    List<Member> findFirstByName(String name, Sort sort);

    Page<Member> findByName(String name, Pageable pageable);

    @Query(value="select * from member limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();

}
