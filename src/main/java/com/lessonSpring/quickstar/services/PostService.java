package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.entities.PostEntity;

import java.util.List;

public interface PostService {
    PostEntity save(PostEntity post);

    List<PostEntity> findAll();
}
