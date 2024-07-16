package com.lec.spring.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t7_attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id")
    private Long post; // 특정 글의 첨부파일? (FK)

    @Column(nullable = false)
    private String sourcename;   // 원본 파일명

    @Column(nullable = false)
    private String filename; // 저장된 파일명 (rename 된 파일명)

    @Transient
    private boolean isImage; // 이미지 여부 (표현할 떄 활용할 용도) -> 기본적으로 false


}
