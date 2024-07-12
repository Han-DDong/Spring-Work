package com.lec.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode. 이게 다 포함된 애너테이션
@NoArgsConstructor //  기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다.
@Builder // builder pattern 사용 가능
public class Post {
    // @Builder 를 사용하면 이렇게 간단하게 객체를 생성할 수 있음.
    private Long id;
    private String user; // 작성자
    private String subject; // 제목
    private String content; // 내용
    private LocalDateTime regDate; // 작성일
    private Long viewCnt; // 조회수
}
