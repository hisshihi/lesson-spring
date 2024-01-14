package com.lessonSpring.quickstar.dao.impl;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl authorDao;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createTestAuthor();

        authorDao.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Hiss"), eq(21)
        );
    }

    //    Создаём тест который будет проверять правильность SQL кода
    @Test
    public void testThatFindOneGeneratesCorrectSql() {
        authorDao.findOne(1L);

//        Проверяем шаблон обновления
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(), eq(1L));
    }

    //    Метод для поиска всех авторов
    @Test
    public void testThatFindManyGeneratesCorrectSql() {
        authorDao.findAll();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    //    Метод для обновления данных
    @Test
    public void testThatUpdateGeneratesCorrectSq() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.update(3L, author);

        /*
         * verify - метод из фреймворка Mockito,
         *  который используется для проверки того, что метод был вызван с определёнными аргументами.
         *  В вашем тесте вы используете его для проверки того, что метод jdbcTemplate.query() был вызван с правильным SQL-запросом.
         * */
        verify(jdbcTemplate).update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?", 1L, "Hiss", 21, 3L);
    }

}
