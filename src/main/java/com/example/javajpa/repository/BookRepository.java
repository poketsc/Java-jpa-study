package com.example.javajpa.repository;

import com.example.javajpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
//    이렇게 소프트딜리트 메서드를 만들면 모든 레퍼지토리에서 다 만들어야한다.
//    그래서 엔티티 클래스에서 처리하는게 좋다. @Where()
    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

//    nativeQuery는 사용하는 디비가 바뀌면 문제가 발생할수도있다.
//    성능 개선을 위해서만 사용하는게 좋다.
//     nativeQuery를 사용하면 entity에서 설정한 값들을 다 무시하고 쿼리문만 작동된다.
    @Query(value= "select * from book", nativeQuery = true)
    List<Book> findAllCustom();

    @Transactional
    @Modifying
    @Query(value = "update book set category = 'JAVA'", nativeQuery = true)
    int updateCategories();

    @Query(value="show tables", nativeQuery = true)
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();

}
