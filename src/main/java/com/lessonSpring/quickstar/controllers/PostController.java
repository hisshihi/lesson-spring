package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.domain.dto.PostDto;
import com.lessonSpring.quickstar.domain.entities.PostEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import com.lessonSpring.quickstar.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {

    private PostService postService;

    private Mapper<PostEntity, PostDto> postMapping;

    public PostController(PostService postService, Mapper<PostEntity, PostDto> postMapping) {
        this.postService = postService;
        this.postMapping = postMapping;
    }

    @PostMapping(path = "/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostEntity post = postMapping.mapFrom(postDto);
        PostEntity savedPost = postService.save(post);
        return new ResponseEntity<>(postMapping.mapTo(savedPost), HttpStatus.CREATED);
    }

    @GetMapping(path = "/posts")
    public List<PostDto> listPost() {
        List<PostEntity> posts = postService.findAll();
        return posts.stream().map(postMapping::mapTo).collect(Collectors.toList());
    }
}
