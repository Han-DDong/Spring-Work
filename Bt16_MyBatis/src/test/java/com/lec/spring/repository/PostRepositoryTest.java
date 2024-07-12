package com.lec.spring.repository;

import com.lec.spring.domain.Post;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 context 를 로딩하여 테스트에 사용
class PostRepositoryTest {

    // MyBatis 가 생성한 구현체들이 담겨 있는 SqlSession 객체
    // 기본적으로 스프링에 빈 생성되어있어서 주입 받을수 있다
    // SqlSession 은 MyBatis 를 통해 데이터베이스와 상호작용하는 데 사용됩니다.
    @Autowired
    private SqlSession sqlSession;

    @Test
    void test0() {
        // getMapper : SQL 문과 Java 메서드를 매핑하는 인터페이스 이고 mapper 의 xml 파일의 sql 문과 연결됨.
        // sqlSession.getMapper(PostRepository.class)를 통해 PostRepository 인터페이스의 구현체를 얻어 SQL 쿼리를 실행합니다.
        PostRepository postRepository = sqlSession.getMapper(PostRepository.class);

        System.out.println("[최초]");
        postRepository.findAll().forEach(System.out::println);

        Post post = Post.builder()
                .user("하기싫어요")
                .subject("진짜 싫어요")
                .content("그만")
                .build();
        System.out.println("[생성전] " + post);
        int result = postRepository.save(post);
        System.out.println("[생성후] " + post);
        System.out.println("save() 결과:" + result);

        System.out.println("[신규 생성후]");
        postRepository.findAll().forEach(System.out::println);

        Long id = post.getId();
        for(int i = 0; i < 5; i++){
            postRepository.incViewCnt(id);
        }
        post = postRepository.findById(id);
        System.out.println("[조회수 증가후] " + post);

        post.setContent("멘탈 박살");
        post.setSubject("시험 포기");
        postRepository.update(post);
        post = postRepository.findById(id);
        System.out.println("[수정후] " + post);

        postRepository.delete(post);
        System.out.println("[삭제후]");
        postRepository.findAll().forEach(System.out::println);
    }

}