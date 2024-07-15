package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "t8_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 회원 아이디

    @JsonIgnore // json 변환시 안보여줌
    @Column(nullable = false)
    private String password; // 회원 비밀번호

    @ToString.Exclude   // toString() 에서 제외
    @JsonIgnore
    @Transient  // Entity 에 반영안함. (form 데이터를 받거나, 검증하는 용도)
    private String re_password; // 비밀번호 확인 입력

    @Column(nullable = false)
    private String name; // 회원 이름
    private String email; // 회원 이메일

    // fetch 기본값
    // @OneToMany, @ManyToMany -> FetchType.Lazy
    // @ManyToONe, @OneToOne -> FetchType.EAGER
    @ManyToMany(fetch = FetchType.EAGER) // <- 유저를 불러올 때 권한도 같이 불러옴
    @ToString.Exclude
    @JsonIgnore
    @Builder.Default    // 필드의 기본값을 유지할 수 있다.
    private List<Authority> authorities = new ArrayList<>();

    // 도우미 메소드
    public void addAuthority(Authority... authorities) {
        Collections.addAll(this.authorities, authorities);
    }

    // OAuth2 Client
    private String provider;
    private String providerId;
}
