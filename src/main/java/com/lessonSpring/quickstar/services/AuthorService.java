package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    AuthorEntity save(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();


    List<AuthorEntity> findByName(String name);

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity patrialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
