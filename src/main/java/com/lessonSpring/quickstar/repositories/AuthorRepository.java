package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Также указываем, что этот класс является репозиторием
@Repository
// Наследуемся от репозитория crud и указываем что туда помещяем и его тип id
public interface AuthorRepository extends CrudRepository<Author, Long> {



}
