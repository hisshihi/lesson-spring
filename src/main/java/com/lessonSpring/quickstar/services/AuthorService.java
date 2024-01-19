package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.dto.AuthorDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();


    List<AuthorEntity> findByName(String name);

    Optional<AuthorEntity> findOne(Long id);
}
