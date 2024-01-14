package com.lessonSpring.quickstar.dao;

import com.lessonSpring.quickstar.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);

    List<Book> findAll();

    void update(String isbn, Book book);
}
