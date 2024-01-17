package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
public class BookRepositoryIntegrationTests {


    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository bookDao) {
        this.underTest = bookDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
//        Создаём автора
        Author author = TestDataUtil.createTestAuthor();
//        Создаём новую книгу
        Book book = TestDataUtil.createTestBook(author);
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
        System.out.println(book);
    }

    @Test
    public void testThatMultipleBooksBeCreatedAndRecallerd() {
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        underTest.save(book);

        Author authorA = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(authorA);
        underTest.save(bookA);

        Author authorB = TestDataUtil.createTestAuthorB();
        Book bookB = TestDataUtil.createTestBookB(authorB);
        underTest.save(bookB);

        Iterable<Book> books = underTest.findAll();
        assertThat(books)
                .hasSize(3)
                .containsExactly(book, bookA, bookB);
        int count = 0;
        for (Book soutBook : books) {
            count++;
            System.out.println("Book " + count + " : " + soutBook);
        }
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        underTest.save(book);
        book.setTitle("UPDATED");
        underTest.save(book);

        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(book);
        System.out.println(result);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);
        underTest.save(book);

        Author authorA = TestDataUtil.createTestAuthorA();
        Book bookA = TestDataUtil.createTestBookA(authorA);
        underTest.save(bookA);

        underTest.deleteById(bookA.getIsbn());

//        Optional<Book> result = underTest.findOne(book.getIsbn());
//        assertThat(result).isNotPresent();

        Iterable<Book> results = underTest.findAll();

        assertThat(results).hasSize(1).containsExactly(book);

        for (Book result : results) {
            System.out.println(result);
        }
    }

}
