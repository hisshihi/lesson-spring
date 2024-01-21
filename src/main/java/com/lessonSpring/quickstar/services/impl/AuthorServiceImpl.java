// Уровень сервиса
package com.lessonSpring.quickstar.services.impl;

import com.lessonSpring.quickstar.domain.dto.AuthorDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.repositories.AuthorRepository;
import com.lessonSpring.quickstar.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    @Override
    public List<AuthorEntity> findAll() {
        /*
        * С помощью потоков итерируемся по репозиторию
        * Указыаем, что нам не нужна паралельная работа
        * Собираем полученные данные в список
        * */
        return StreamSupport.stream(authorRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<AuthorEntity> findByName(String name) {
        return StreamSupport.stream(authorRepository.searchByName(name).spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorEntity patrialUpdate(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);

        return authorRepository.findById(id).map(existingAuthor -> {
            /*
            * Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
			*  Эта инструкция проверяет, не равно ли поле name объекта authorEntity null.
			*  Если оно не равно null, то метод setName() вызывается с параметром, равным значению поля name объекта authorEntity.
            * */
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
            Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Автор не существует"));
    }


}
