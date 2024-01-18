package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.data.DataUtil;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

    AuthorEntity authorEntity = DataUtil.createTestAuthor();

//    Получение книги
    @GetMapping(path = "/books")
    public BookEntity retrieveBook() {
        return BookEntity.builder()
                .isbn("235195")
                .title("My Story")
                .authorEntity(authorEntity)
                .build();
    }

    @PostMapping(path = "/books")
    public BookEntity createBook(@RequestBody final BookEntity bookEntity) {
        log.info("Got book: " + bookEntity.toString());
        return bookEntity;
    }

}
