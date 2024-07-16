package com.lec.spring.repository;

import com.lec.spring.domain.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
public class JpaBoard {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void JB() {
        // Authority 생성
        System.out.println("Authority 생성 " + "-".repeat(50));
        Authority auth_member = Authority.builder()
                .name("ROLE_MEMBER")
                .build();
        Authority auth_admin = Authority.builder()
                .name("ROLE_ADMIN")
                .build();

        authorityRepository.saveAndFlush(auth_member); // insert
        authorityRepository.saveAndFlush(auth_admin); // insert

        authorityRepository.findAll().forEach(System.out::println); // select

        // user 생성
       User user1 = User.builder()
               .username("USER")
               .password(passwordEncoder.encode("1234"))
               .name("유저")
               .email("user@gmail.com")
               .build();

       User admin1 = User.builder()
               .username("ADMIN")
               .password(passwordEncoder.encode("1234"))
               .name("관리자")
               .email("admin@gmail.com")
               .build();

        user1.addAuthority(auth_member);
        admin1.addAuthority(auth_admin, auth_member);

        userRepository.saveAll(List.of(user1, admin1));  // INSERT
        userRepository.findAll().forEach(System.out::println);  // SELECT

        // 특정 User 권한 조회
        System.out.println("\nUser 및 권한 조회" + "-".repeat(20));
        user1 = userRepository.findById(1L).orElse(null);   // EAGER Fetch. User 를 읽어올때 Authority 까지 불러옴.
        admin1 = userRepository.findById(2L).orElse(null);
        System.out.println(user1.getAuthorities()); // ROLE_MEMBER! 만 있어야함.
        System.out.println(admin1.getAuthorities());

        // 글 post 작성
        System.out.println("\nPost 작성" + "⭐️".repeat(50));

        Post p1 = Post.builder()
                .subject("제목1")
                .content("회원임~")
                .user(user1)
                .build();

        Post p2 = Post.builder()
                .subject("제목2")
                .content("관리자임~")
                .user(admin1)
                .build();

        postRepository.saveAll(List.of(p1, p2));
        postRepository.findAll().forEach(System.out::println);

        // 글 post 동작
        System.out.println("\nPost 동작" + "-".repeat(50));
        {
            Post post = postRepository.findById(1L).orElse(null);

            System.out.println(post);
            System.out.println(post.getUser());
            System.out.println(post.getUser().getAuthorities());
        }

        // 첨부파일 추가
        System.out.println("\n첨부파일 추가" + "-".repeat(50));

        Attachment attachment1 = Attachment.builder()
                .filename("face01.png")
                .sourcename("face01.png")
                .post(p1.getId())
                .build();
        Attachment attachment2 = Attachment.builder()
                .filename("face02.png")
                .sourcename("face02.png")
                .post(p2.getId())
                .build();

        attachmentRepository.saveAll(List.of(
           attachment1, attachment2
        ));

        attachmentRepository.findAll().forEach(System.out::println);

        // 댓글
        System.out.println("\n댓글 생성 " + "-".repeat(50));
        Comment c1 = Comment.builder()
                .content("1. user1 댓글입니다~")
                .user(user1)
                .post(p1.getId())
                .build();
        Comment c2 = Comment.builder()
                .content("2. admin1 관리자 댓글담~")
                .user(admin1)
                .post(p2.getId())
                .build();

        commentRepository.saveAll(List.of(c1, c2));

        commentRepository.findAll().forEach(System.out::println);
    }
}
