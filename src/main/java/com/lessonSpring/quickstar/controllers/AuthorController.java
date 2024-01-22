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
import java.util.Optional;
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
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.CREATED);
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
    @GetMapping(path = "/authors/name/{name}")
    public List<AuthorDto> findAuthorsByName(@PathVariable("name") String name) {
        List<AuthorEntity> authors = authorService.findByName(name);
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());
    }

    //    Чтение всех авторов по id
    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable("id") Long id) {
        Optional<AuthorEntity> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(authorEntity -> {
            AuthorDto authorDto = authorMapper.mapTo(authorEntity);
            return new ResponseEntity(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    //    Метод полного обновления автора
    @PutMapping(path = "authors/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
//        Проверяем, существует ли автор
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

//        Устанавливаем id, чтобы можно было полностью обновить автора
        authorDto.setId(id);
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
//        Усовершенствуем текущий метод сохранения для того, чтобы можно было полностью обновлять автора
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthorEntity), HttpStatus.OK);

    }

//    Частичное обновление автора
    @PatchMapping(path = "authors/{id}")
    public ResponseEntity<AuthorDto> partialPatch(@PathVariable("id") Long id, @RequestBody AuthorDto authorDto) {
        if (!authorService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AuthorEntity authorEntity = authorMapper.mapFrom(authorDto);
        AuthorEntity updatedAuthorEntity = authorService.patrialUpdate(id, authorEntity);
        return new ResponseEntity<>(authorMapper.mapTo(updatedAuthorEntity), HttpStatus.OK);
    }

//    Метод для удаления автора
    @DeleteMapping(path = "authors/{id}")
    public ResponseEntity deleteAuthors(@PathVariable("id") Long id) {
        authorService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
