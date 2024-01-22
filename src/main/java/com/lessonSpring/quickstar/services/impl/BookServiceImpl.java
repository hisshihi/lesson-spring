package com.lessonSpring.quickstar.services.impl;

import com.lessonSpring.quickstar.domain.dto.BookDto;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import com.lessonSpring.quickstar.repositories.BookRepository;
import com.lessonSpring.quickstar.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createUpdateBook(String isbn, BookEntity bookEntity) {
//        Устанавливаем isbn для того, чтобы избежать проблем
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return bookRepository.findById(isbn);
    }

    @Override
    public boolean isExist(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public BookEntity patrialUpdate(String isbn, BookEntity bookEntity) {
//        Указываем isbn для book, чтобы убедиться, что это именно тот book
        bookEntity.setIsbn(isbn);
        return bookRepository.findById(isbn).map(existingBook -> {
//            Проверяем, не равно ли title null, если не равно, тогда устанавливаем тот title который пришёл
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new RuntimeException("Книга не существует"));
    }


}
