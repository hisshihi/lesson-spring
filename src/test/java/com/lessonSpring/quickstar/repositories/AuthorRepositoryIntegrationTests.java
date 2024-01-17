package com.lessonSpring.quickstar.repositories;

import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {

//        Создаём нового автора
        Author author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
        System.out.println(author);

    }

//    Создаём нескольких авторов и тестируем
    @Test
    public void testThatMultipleAuthorsBeCreatedAndRecallerd() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.findAll();
        assertThat(result)
                .hasSize(3)
                .containsExactly(author, authorA, authorB);
        System.out.println(result);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);
        System.out.println(authorB);
        authorB.setName("Солнышко");
        underTest.save(authorB);

        Optional<Author> result =  underTest.findById(authorB.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorB);
        System.out.println(result);
        System.out.println(authorB);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        underTest.deleteById(author.getId());

        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.save(authorA);

        Iterable<Author> results = underTest.findAll();
//        assertThat(result).isNotPresent();
//        assertThat(result.get()).isEqualTo(author);
        System.out.println(results);
    }

//    Создаём свой метод для поиска авторов по возрасту
    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.createTestAuthorA();
        authorA.setAge(24);
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        authorB.setAge(19);
        underTest.save(authorB);

        Iterable<Author> results = underTest.ageLessThan(22);
        assertThat(results).containsExactly(author, authorB);
        System.out.println(results);

    }

//    Метод поиска авторов по имени
    @Test
    public void testThatGetAuthorsWithSearchByName() {

        Author author = TestDataUtil.createTestAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.createTestAuthorA();
        authorA.setName("Денис");
        underTest.save(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.searchByName("Денис");
        assertThat(result).containsExactly(author, authorA);
        System.out.println(result);
    }

}
