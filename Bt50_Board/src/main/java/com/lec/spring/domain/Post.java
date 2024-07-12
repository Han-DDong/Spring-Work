package com.lec.spring.domain;


import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode. 이게 다 포함된 애너테이션
@NoArgsConstructor //  기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다.
@Builder // builder pattern 사용 가능
public class Post {
    // @Builder 를 사용하면 이렇게 간단하게 객체를 생성할 수 있음.
    private Long id;
    private String subject; // 제목
    private String content; // 내용
    private LocalDateTime regDate; // 작성일
    private Long viewCnt; // 조회수

    private User user; // 글 작성자 (FK)

    // 첨부파일
    @ToString.Exclude
    @Builder.Default // builder 제공안함
    private List<Attachment> fileList = new ArrayList<>();
}
