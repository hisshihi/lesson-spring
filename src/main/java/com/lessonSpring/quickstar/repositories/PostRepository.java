package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.domain.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
