package com.lessonSpring.quickstar.dao.impl;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.dao.AuthorDao;
import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)

/*
 * @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) - это аннотация в Spring Boot,
 *  которая указывает, что после каждого метода теста необходимо очищать контекст приложения.
 *  Это означает, что все бобы, созданные в контексте приложения, будут уничтожены после каждого теста, и при следующем тесте будет создан новый контекст приложения.

 *  Это полезно для обеспечения независимости тестов друг от друга.
 *  Если бы контекст приложения не очищался после каждого теста, то изменения, сделанные в контексте одним тестом,
 *  могли бы повлиять на другие тесты. Это могло бы привести к ошибкам и нестабильности тестов.
 * */
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

    @Test
    public void testThatMultipleBooksBeCreatedAndRecallerd() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        Author authorA = TestDataUtil.createTestAuthorA();
        authorDao.create(authorA);
        Book bookA = TestDataUtil.createTestBookA();
        bookA.setAuthorId(authorA.getId());
        underTest.create(bookA);

        Author authorB = TestDataUtil.createTestAuthorB();
        authorDao.create(authorB);
        Book bookB = TestDataUtil.createTestBookB();
        bookB.setAuthorId(authorB.getId());
        underTest.create(bookB);

        List<Book> books = underTest.findAll();
        assertThat(books)
                .hasSize(3)
                .containsExactly(book, bookA, bookB);
        int count = 0;
        for (Book soutBook : books ) {
            count++;
            System.out.println("Book " + count + " : " + soutBook);
        }
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthor();
        authorDao.create(author);
        Book book = TestDataUtil.createTestBook();
        underTest.create(book);
        book.setTitle("UPDATED");
        book.setAuthorId(author.getId());
        underTest.update(book.getIsbn(), book);

        Optional<Book> result = underTest.findOne(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(book);
        System.out.println(result);
    }

}
