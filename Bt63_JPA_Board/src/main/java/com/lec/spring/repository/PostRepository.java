package com.lec.spring.repository;

// Repository layer(aka. Data layer)
// DataSource (DB) 등에 대한 직접적인 접근


//        PostRepository 란?
//        PostRepository 는 데이터베이스와 상호작용하는 역할을 합니다.
//        쉽게 말해, 데이터베이스에서 데이터를 가져오거나 저장하는 일을 담당하는 '도우미'라고 생각하면 됩니다.
//
//        왜 PostRepository 를 사용할까요?
//        데이터베이스와 직접 상호작용하는 코드를 우리가 직접 작성하지 않고,
//        PostRepository 가 그 일을 대신해줍니다.
//        이렇게 하면 코드가 훨씬 간단해지고, 반복되는 작업을 줄일 수 있습니다.
//
//        주요 기능
//        PostRepository 는 기본적으로 다음과 같은 작업을 쉽게 할 수 있게 해줍니다:
//
//       1. 저장하기: 새로운 게시글을 데이터베이스에 저장합니다.

//       2. 읽기: 모든 게시글을 가져오거나, 특정 게시글을 ID로 찾아서 가져옵니다.

//       3. 업데이트: 기존 게시글을 수정합니다.

//       4. 삭제하기: 특정 게시글을 데이터베이스에서 삭제합니다.


import com.lec.spring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

}
