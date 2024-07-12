package com.lec.spring.di05;

import com.lec.spring.beans.MessageBean;
import com.lec.spring.beans.Score;
import com.lec.spring.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Config05 {
    public Config05() {
        System.out.println("Config05() 생성");
    }

    @Bean
    public Score scoreA() {
        return new Score(77, 55, 89, "굳굳굳굳굳굳");
    }

    @Bean(name="사천왕")
    public Student stu1() {
        return new Student("조심", 19, scoreA());
    }

    @Bean
    public Student stu2() {
        return new Student("한정우", 25, scoreA());
    }

    @Bean
    @Primary // 우선 주입됨
    public MessageBean msg1() {
        return  new MessageEng();
    }

    @Bean
    public MessageBean msg2() {
        return new MessageKor();
    }
}
