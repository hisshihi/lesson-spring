// Слой представления
package com.lessonSpring.quickstar.controllers;

import com.lessonSpring.quickstar.domain.dto.AuthorDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.mappers.Mapper;
import com.lessonSpring.quickstar.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

//    Создаём экземпляр интерфейса, чтобы использовать его методы
    private AuthorService authorService;

    private Mapper<AuthorEntity, com.lessonSpring.quickstar.domain.dto.AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, com.lessonSpring.quickstar.domain.dto.AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

//    Создание нового автора
    @PostMapping(path = "/authors")
    public ResponseEntity<com.lessonSpring.quickstar.domain.dto.AuthorDto> createAuthor(@RequestBody com.lessonSpring.quickstar.domain.dto.AuthorDto author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorService.createAuthor(authorEntity);
        return new ResponseEntity<>( authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
    }

//    Контроллер для отображения всех авторов
    @GetMapping(path = "/authors")
    public List<AuthorDto> listAuthors() {
        List<AuthorEntity> authors = authorService.findAll();
        return authors.stream()
                .map(authorMapper::mapTo)
                .collect(Collectors.toList());
    }

//    Контроллер для поиска автора по имени
    @GetMapping(path = "/authors/{name}")
    public List<AuthorDto> findAuthorsByName(@PathVariable("name") String name) {
        List<AuthorEntity> authors = authorService.findByName(name);
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }



}
