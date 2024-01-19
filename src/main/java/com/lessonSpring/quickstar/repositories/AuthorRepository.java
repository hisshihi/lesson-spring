package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Также указываем, что этот класс является репозиторием
@Repository
// Наследуемся от репозитория crud и указываем что туда помещяем и его тип id
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {


    Iterable<AuthorEntity> ageLessThan(int age);

    Iterable<AuthorEntity> searchByName(String name);

    /*
    * Создаём пользовательский запрос
    * Добавляем аннотацию query для поиска автора
    * Указываем условие по которому мы будем искать:
    ** SELECT a FROM Author a: эта часть запроса выбирает все экземпляры класса Author и присваивает им псевдоним a.
     * where a.age > ?1: этот оператор фильтрует результаты запроса, включая только тех авторов,
     *  чей возраст больше, чем значение, заданное в качестве параметра ?1.
    * */
    @Query("SELECT author FROM AuthorEntity author where author.age > ?1")
    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThat(int age);
}
