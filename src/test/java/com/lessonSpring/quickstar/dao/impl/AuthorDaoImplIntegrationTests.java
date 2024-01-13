package com.lessonSpring.quickstar.dao.impl;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaoImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTests(AuthorDaoImpl authorDao) {
        this.underTest = authorDao;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

//        Создаём нового автора
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
        System.out.println(author);

    }

//    Создаём нескольких авторов и тестируем
    @Test
    public void testThatMultipleAuthorsBeCreatedAndRecallerd() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.create(author);
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);

        List<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(author, authorA, authorB);
        System.out.println(result);
    }

}
