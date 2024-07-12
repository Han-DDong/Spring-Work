package com.lec.spring.repository;

import com.lec.spring.domain.User;

public interface UserRepository {

// 특정 id (PK) 의 user 리턴
User findById(Long id); // (SELECT)


// 특정 username 의 user 리턴
// ⬆️ 아이디는 중복 될 수 없기 때문에
User findByUsername(String username); // (SELECT)


// 새로운 User 등록
int save(User user); // (INSERT)


// User 정보 수정
int update(User user); // (UPDATE)


}
