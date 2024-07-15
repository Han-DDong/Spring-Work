package com.lec.spring.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data // @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode. 이게 다 포함된 애너테이션
@NoArgsConstructor //  기본 생성자를 생성합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 생성합니다.
@Builder // builder pattern 사용 가능
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "t8_post")
@DynamicInsert  // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject; // 제목

    @Column(columnDefinition = "LONGTEXT")
    private String content; // 내용

    @ColumnDefault(value = "0")
    @Column(insertable = false)
    private Long viewCnt; // 조회수

    // Post : User = N:1
    @ManyToOne(optional = false) // 작성한 글에는 반드시 작성자가 있어야 함.
    // optional = false : user_id bigint not null
    // 이게 없으면 Post 의 find..동작시  Post 와 User 사이에 left outer join 발생
    @ToString.Exclude
    private User user; // 글 작성자 (FK)

    // 첨부파일
    @ToString.Exclude
    @Builder.Default // builder 제공안함
    @OneToMany(cascade = CascadeType.ALL)
    // Post:File = 1:N
    // cascade = CascadeType.ALL  : 삭제등의 동작 발생시 child 도 함께 삭제
    @JoinColumn(name = "post_id")
    private List<Attachment> fileList = new ArrayList<>();

    public void addFiles(Attachment... files){
        Collections.addAll(fileList, files);
    }

    // 댓글
    // Post : Comment = 1:N
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    public void addComments(Comment... comments){
        Collections.addAll(commentList, comments);
    }
}
