package com.lessonSpring.quickstar.dao.impl;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.dao.AuthorDao;
import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaoImplIntegrationTest {

//    Создаём автора, чтобы провести интеграционный тест
    private AuthorDao authorDao;

    private BookDaoImpl underTest;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl bookDao, AuthorDao authorDao) {
        this.underTest = bookDao;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
//        Создаём автора
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
//        Создаём новую книгу
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
        System.out.println(book);
    }

}
