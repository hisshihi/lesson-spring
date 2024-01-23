package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.domain.dto.PostDto;
import com.lessonSpring.quickstar.domain.entities.PostEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import com.lessonSpring.quickstar.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private PostService postService;

    private Mapper<PostEntity, PostDto> postMapping;

    public PostController(PostService postService, Mapper<PostEntity, PostDto> postMapping) {
        this.postService = postService;
        this.postMapping = postMapping;
    }

    @PostMapping(path = "post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostEntity post = postMapping.mapFrom(postDto);
        PostEntity savedPost = postService.save(post);
        return new ResponseEntity<>(postMapping.mapTo(savedPost), HttpStatus.CREATED);
    }
}
