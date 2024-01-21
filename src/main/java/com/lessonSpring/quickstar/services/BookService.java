package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.dto.BookDto;
import com.lessonSpring.quickstar.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {

//    Для создания книг взаимодействуем с сервисным уровнем
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExist(String isbn);
}
