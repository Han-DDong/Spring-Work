package com.lec.spring.domain;

import com.lec.spring.listener.Auditable;
import com.lec.spring.listener.UserEntityListener;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor // NonNull 이 붙은 생성사 하나 더 만듬.
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)

// Entity 객체 지정. 이 객체가 JPA 에서 관리하는 Entity 객체임을 알림.
@Entity  // user 라는 테이블을 만들 수 없기 때문에 @Entity 에서 테이블의 이름을 직접 지정해줌 (Entity 객체 지정)
@Table(
        name = "T_USER" // DB 테이블명
        , indexes = {@Index(columnList = "name")}   // 컬럼에 대한 index 생성
        , uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "name"})})  // unique 제약사항
@EntityListeners(value = {UserEntityListener.class})
public class User extends BaseEntity{

    @Id  // PK 지정 필수요소 ( JPA 에서는 반드시 테이블에 PK 있어야함 )
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 마치 MySQL 의 AUTO_INCREMENT 와 같은 동작 수행
    private Long id;

    @NonNull // DB 에서 NOT NULL 이랑 똑같음.
    private String name;

    @NonNull
    @Column(unique = true)
    private String email;

//    @Column(name = "crtdat", nullable = false)
//    @Column(updatable = false) // UPDATE 동작 시 해당 컬럼은 생략.
//    @CreatedDate    // AuditingEntityListener 가 Listener 로 적용시 사용
//    private LocalDateTime createdAt;  // created_at

//    @Column(insertable = false) // INSERT 동작 시 해당 컬럼은 생략.
//    @LastModifiedDate   // AuditingEntityListener 가 Listener 로 적용시 사용
//    private LocalDateTime updatedAt;

    // User:Address => 1:N
//    @OneToMany(fetch =  FetchType.EAGER)
//    private List<Address> address;

    @Transient // jakarta.persistence   DB 에 반영 안하는 필드 속성.  영속성을 부여 안함.
    private String testDate; // test_data

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

//    @PrePersist // INSERT 하기 전
//    public void prePersist() {
//        System.out.println(">> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PreUpdate // UPDATE 하기 전
//    public void preUpdate() {
//        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreRemove // DELETE 하기 전
//    public void preRemove() {
//        System.out.println(">>> preRemove");
//    }
//
//    @PostPersist // INSERT 직후
//    public void postPersist() {
//        System.out.println(">>> postPersist");
//    }
//
//    @PostUpdate // UPDATE 직후
//    public void postUpdate() {
//        System.out.println(">>> postUpdate");
//    }
//
//    @PostRemove // DELETE 직후
//    public void postRemove() {
//        System.out.println(">>> postRemove");
//    }
//
//    @PostLoad // SELECT 직후
//    public void postLoad() {
//        System.out.println(">>> postLoad");
//    }


}
