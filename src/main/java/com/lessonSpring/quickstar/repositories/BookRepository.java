package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
}
