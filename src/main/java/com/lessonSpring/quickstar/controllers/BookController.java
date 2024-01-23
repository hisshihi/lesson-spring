package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.domain.dto.BookDto;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import com.lessonSpring.quickstar.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private Mapper<BookEntity, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<BookEntity, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

//    Обновляем метод для того, чтобы можно было создавать и обновлять книги
    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
//        Из bookEntity отдаём данные в bookDto
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);

        boolean bookExists = bookService.isExist(isbn);

        BookEntity savedBookEntity = bookService.createUpdateBook(isbn, bookEntity);
//        Отображаем данные сохранённой книги
        BookDto savedBookDto = bookMapper.mapTo(savedBookEntity);
//        Для начала проверяем, существует ли книга, если да, то обновляем, если нет, то создаём
        if (bookExists) {
//            Обновляем книгу
            return new ResponseEntity<>(savedBookDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);
        }
    }

    //    Отображение всех книг
    @GetMapping("/books")
    public Page<BookDto> listBooks(Pageable pageable) {
        Page<BookEntity> books = bookService.findAll(pageable);
        return books.map(bookMapper::mapTo);
    }

//    Чтение одной книги
    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<BookEntity> findBook = bookService.findOne(isbn);
        return findBook.map(bookEntity -> {
            BookDto bookDto = bookMapper.mapTo(bookEntity);
            return new ResponseEntity(bookDto, HttpStatus.OK);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

//    Частичное обновление книги
    @PatchMapping("/books/{isbn}")
    public ResponseEntity<BookDto> patrialPathBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        boolean bookExists = bookService.isExist(isbn);
        if (!bookExists) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        // Преобразовываем объект dto в сущность для работы с ним
        BookEntity bookEntity = bookMapper.mapFrom(bookDto);
        BookEntity updatedBookEntity = bookService.patrialUpdate(isbn, bookEntity);
        return new ResponseEntity<>(bookMapper.mapTo(updatedBookEntity), HttpStatus.OK);

    }

//    Удаление книги
    @DeleteMapping(path = "books/{isbn}")
    public ResponseEntity deleteBook(@PathVariable("isbn") String isbn) {
        bookService.delete(isbn);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
