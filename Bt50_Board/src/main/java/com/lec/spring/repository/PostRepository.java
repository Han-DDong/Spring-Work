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

import java.util.List;



public interface PostRepository {

    // 새 글 작성 (INSERT) <- Post(작성자, 제목, 내용)
    int save(Post post);

    // 특정 id 글 내용 읽기 (SELECT)  =>  Post
    // 만약 해당 id 의 글이 없으면 null 리턴
    Post findById(Long id);

    // 특정 id 글의 조회수를 +1 증가 (UPDATE)
    int incViewCnt(Long id);

    // 전체 글 목록. 최신순으로 읽어옴 (SELECT) => List<>;
    List<Post> findAll();

    // 특정 id 글 수정하기 ( 제목, 내용 ) (UPDATE)
    int update(Post post);

    // 특정 id 글 삭제하기 (DELETE) <- Post(id)
    int delete(Post post);


    // 페이징
    // from (몇 번째) 부터 rows (몇 개) 만큼 SELECT
    List<Post> selectFromRow(int from, int rows);

    // 전체 글의 개수
    int countAll();

}
