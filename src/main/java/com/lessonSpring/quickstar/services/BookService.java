package com.lessonSpring.quickstar.services;

import com.lessonSpring.quickstar.domain.entities.BookEntity;

public interface BookService {

//    Для создания книг взаимодействуем с сервисным уровнем
    BookEntity createBook(String isbn, BookEntity bookEntity);

}
