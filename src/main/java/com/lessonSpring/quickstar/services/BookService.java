package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

//    Для создания книг взаимодействуем с сервисным уровнем
    BookEntity createUpdateBook(String isbn, BookEntity bookEntity);

    List<BookEntity> findAll();

//    Перегружаем метод для того, чтобы использовать пагинацию и сортировку
    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    boolean isExist(String isbn);

    BookEntity patrialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
