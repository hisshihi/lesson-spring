package com.lessonSpring.quickstar.services.impl;

import com.lessonSpring.quickstar.domain.entities.PostEntity;
import com.lessonSpring.quickstar.repositories.PostRepository;
import com.lessonSpring.quickstar.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity save(PostEntity post) {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM HH:mm");
        String dateTime = formatter.format(time);
        post.setTime(dateTime);
        return postRepository.save(post);
    }

    @Override
    public List<PostEntity> findAll() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
}
