package com.lec.spring.repository;

import com.lec.spring.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 컨테이너에 있는 bean 객체 전부 생성 해놓고 test 진행
class UserRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    @Test
    void test1() {
        UserRepository userRepository = sqlSession.getMapper(UserRepository.class);
        AuthorityRepository authorityRepository = sqlSession.getMapper(AuthorityRepository.class);

        User user = userRepository.findById(3L);
        System.out.println("findById(): " + user);
        var list = authorityRepository.findByUser(user);
        System.out.println("권한: " + list);

        user = User.builder()
                .username("USER3")
                .password(encoder.encode("1234"))
                .name("회원3")
                .email("user3@mail.com")
                .build();
        System.out.println("sava() 전: " + user);
        userRepository.save(user);
        System.out.println("sava() 후: " + user);

        authorityRepository.addAuthority(user.getId(), authorityRepository.findByName("ROLE_MEMBER").getId());
        System.out.println("권한: " + authorityRepository.findByUser(user));
    }

    @Autowired
    PasswordEncoder encoder;

    @Test
    void passwordEncoderTest(){
        String rawPassword = "1234";

        for(int i = 0; i < 10; i++){
            // 암호화 알고리즘은 매번 다른 결과 리턴
            System.out.println(encoder.encode(rawPassword));
        }
    }
}




























