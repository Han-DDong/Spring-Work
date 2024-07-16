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
@Entity(name = "t7_user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 회원 아이디

    @JsonIgnore // json 변환시 안보여줌
    @Column(nullable = false)
    private String password; // 회원 비밀번호

    @ToString.Exclude
    @JsonIgnore
    @Transient // Entity 반영 X
    private String re_password; // 비밀번호 확인 입력

    @Column(nullable = false)
    private String name; // 회원 이름
    private String email; // 회원 이메일

    @ManyToMany(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    @Builder.Default
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority... authorities) {
        Collections.addAll(this.authorities, authorities);
    }

    // OAuth2 Client
    private String provider;
    private String providerId;
}
