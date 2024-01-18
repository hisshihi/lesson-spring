package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.data.DataUtil;
import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Log
public class BookController {

    Author author = DataUtil.createTestAuthor();

//    Получение книги
    @GetMapping(path = "/books")
    public Book retrieveBook() {
        return Book.builder()
                .isbn("235195")
                .title("My Story")
                .author(author)
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book) {
        log.info("Got book: " + book.toString());
        return book;
    }

}
