package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
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
public class BookEntityRepositoryIntegrationTests {


    private BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository bookDao) {
        this.underTest = bookDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
//        Создаём автора
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        Создаём новую книгу
        BookEntity bookEntity = TestDataUtil.createTestBookEntity(authorEntity);
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
        System.out.println(bookEntity);
    }

    @Test
    public void testThatMultipleBooksBeCreatedAndRecallerd() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBookEntity(authorEntity);
        underTest.save(bookEntity);

        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntityA);
        underTest.save(bookEntityA);

        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
        BookEntity bookEntityB = TestDataUtil.createTestBookB(authorEntityB);
        underTest.save(bookEntityB);

        Iterable<BookEntity> books = underTest.findAll();
        assertThat(books)
                .hasSize(3)
                .containsExactly(bookEntity, bookEntityA, bookEntityB);
        int count = 0;
        for (BookEntity soutBookEntity : books) {
            count++;
            System.out.println("Book " + count + " : " + soutBookEntity);
        }
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBookEntity(authorEntity);
        underTest.save(bookEntity);
        bookEntity.setTitle("UPDATED");
        underTest.save(bookEntity);

        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result).get().isEqualTo(bookEntity);
        System.out.println(result);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBookEntity(authorEntity);
        underTest.save(bookEntity);

        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
        BookEntity bookEntityA = TestDataUtil.createTestBookA(authorEntityA);
        underTest.save(bookEntityA);

        underTest.deleteById(bookEntityA.getIsbn());

//        Optional<Book> result = underTest.findOne(book.getIsbn());
//        assertThat(result).isNotPresent();

        Iterable<BookEntity> results = underTest.findAll();

        assertThat(results).hasSize(1).containsExactly(bookEntity);

        for (BookEntity result : results) {
            System.out.println(result);
        }
    }

}
