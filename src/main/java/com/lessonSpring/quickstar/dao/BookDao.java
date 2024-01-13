package com.lessonSpring.quickstar.dao;

import com.lessonSpring.quickstar.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findOne(String isbn);
}
