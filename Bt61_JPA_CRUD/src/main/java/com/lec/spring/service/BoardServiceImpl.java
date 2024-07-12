package com.lec.spring.service;

import com.lec.spring.domain.Post;
import com.lec.spring.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private PostRepository postRepository;

//    @Override
//    public int write(Post post) {
//        int result = 0;
//        if (post != null) {
//            postRepository.save(post);
//            result = 1;
//        }
//
//        return result;
//    }


    // 강사님
    @Override
    public int write(Post post) {
      postRepository.save(post); // INSERT
        return 1;
    }


//    @Override
//    @Transactional
//    public Post detail(Long id) {
//        Post post = postRepository.findById(id).orElseThrow(RuntimeException::new);
//        if (post.getViewCnt() == null) {
//            post.setViewCnt(0L);
//        }
//        post.setViewCnt(post.getViewCnt()+1);
//        return postRepository.save(post);
//    }

    // 강사님
    @Override
    public Post detail(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if(post != null){
            post.setViewCnt(post.getViewCnt() + 1);
            postRepository.saveAndFlush(post);
        }

        return post;
    }

    @Override
    public List<Post> list() {
        return postRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    @Override
    public Post selectById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

//    @Override
//    public int update(Post post) {
//        int result = 0;
//        if (post != null) {
//            postRepository.save(post);
//            result = 1;
//        }
//
//        return result;
//    }


    @Override
    public int update(Post post) {
        Post data = postRepository.findById(post.getId()).orElse(null);
        if (data == null) return 0;

        data.setSubject(post.getSubject());
        data.setContent(post.getContent());

        postRepository.save(data);  // select + delete
        return 1;
    }


//    @Override
//    public int deleteByID(Long id) {
//        return postRepository.findById(id).map(post -> {
//            postRepository.deleteById(id);
//            return 1;
//        }).orElse(0);
//    }
//

    @Override
    public int deleteByID(Long id) {
        boolean exists = postRepository.existsById(id); // <- 존재하면 true 아니면 false
        if (!exists) return 0;

        postRepository.deleteById(id); // select + delete
        return 1;
    }
}
