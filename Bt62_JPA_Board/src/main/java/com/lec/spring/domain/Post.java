package com.lec.spring.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "t7_post")
@DynamicInsert
@DynamicUpdate
public class Post extends BaseEntity{
    // @Builder 를 사용하면 이렇게 간단하게 객체를 생성할 수 있음.
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

    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user; // 글 작성자 (FK)

    // 첨부파일
    @ToString.Exclude
    @Builder.Default // builder 제공안함
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Attachment> fileList = new ArrayList<>();

    public void addFiles(Attachment... filse) {
        Collections.addAll(fileList, filse);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentList = new ArrayList<>();

    public void addComments(Comment... comments) {
        Collections.addAll(commentList, comments);
    }
}
