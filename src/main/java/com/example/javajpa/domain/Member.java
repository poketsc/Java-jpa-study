package com.example.javajpa.domain;

import com.example.javajpa.domain.listener.Auditable;
import com.example.javajpa.domain.listener.UserEntityListener;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value= {UserEntityListener.class})
// 실제 DB에 설정 안되어있으면 의미 없음
//@Table(name="member", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

//    디폴트가 ordinal 로 되어있는데 이러면 0,1 이렇게 번호가 디비에 저장된다. 중간에 enum 데이터 값을 추가하게 되면 번호들이 바뀌므로 문제가 생길수 있다
//    그래서 STRING 타입을 선택하고 등록하자!
    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

//    @Column
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    @Transient
    private String testData;

//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PostPersist
//    public void postPersist() {
//        System.out.println("postPersist");
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println("postUpdate");
//    }
//
//    @PreRemove
//    public void preRemove() {
//        System.out.println("preRemove");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println("postRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println("postLoad");
//    }

}
