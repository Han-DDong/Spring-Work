package com.lec.spring.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode. 이게 다 포함된 애너테이션
@NoArgsConstructor //  기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다.
@Builder // builder pattern 사용 가능
@Entity(name = "TBL_POST")
public class Post {
    // @Builder 를 사용하면 이렇게 간단하게 객체를 생성할 수 있음.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // ← nullable=false 는 ddl-auto: update 에선 동작하지 않으므로 create 나 create-drop 으로 적용해야 한다.
    private String user; // 작성자
    @Column(nullable = false)
    private String subject; // 제목

    @Column(
            columnDefinition = "LONGTEXT"   // <- MySQL, Postgre 의 경우
//            , length = 1000 // Oracle 의 경우 (varchar2(1000) 으로 지정됨)
    )
    private String content; // 내용

    private LocalDateTime regDate; // 작성일

    @ColumnDefault(value = "0")
    @Column(insertable = false) // insert 할때 null 값으로 두지 않고 설정해둔 디폴트 값 적
    private Long viewCnt; // 조회수
//    private long viewCnt; // primitive 로 선언하면 기본적으로 NN 속성

    @PrePersist
    private void prePersist() {
        this.regDate = LocalDateTime.now();
    }
}
