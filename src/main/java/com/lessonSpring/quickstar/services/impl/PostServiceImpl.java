package com.lessonSpring.quickstar.services.impl;

import com.lessonSpring.quickstar.domain.entities.PostEntity;
import com.lessonSpring.quickstar.repositories.PostRepository;
import com.lessonSpring.quickstar.services.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity save(PostEntity post) {
        return postRepository.save(post);
    }
}
