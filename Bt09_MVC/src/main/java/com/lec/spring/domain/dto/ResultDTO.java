package com.lec.spring.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//@JsonPropertyOrder({ // <- Json 문자열을 이름, 나이 순서로 지정해줌
//        "name",
//        "age"
//})

@Data
public class ResultDTO {

    public String status;

    public Integer code;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @JsonProperty("response_time")
    public LocalDateTime responseTime;

    public List<Result> result;

    @Data
    public static class Result {

        public String name;

        public Integer age;

    }

}