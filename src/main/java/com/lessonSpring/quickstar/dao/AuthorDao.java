package com.lessonSpring.quickstar.dao;

import com.lessonSpring.quickstar.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

//    Если находим автора с этим параметром, то возвращаем его
//    Однако если автор не будет найден будет всё ровно возвращён пустой параметр
    Optional<Author> findOne(long l);

    //    Метод для поиска всех авторов
    List<Author> findAll();

    void update(long id, Author author);
}
