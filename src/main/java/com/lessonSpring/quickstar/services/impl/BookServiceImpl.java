package com.lessonSpring.quickstar.services.impl;

import com.lessonSpring.quickstar.domain.entities.BookEntity;
import com.lessonSpring.quickstar.repositories.BookRepository;
import com.lessonSpring.quickstar.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
//        Устанавливаем isbn для того, чтобы избежать проблем
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }
}