//package com.lessonSpring.quickstar.repositories;
//
//import com.lessonSpring.quickstar.TestDataUtil;
//import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//
///*
//* @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) - это аннотация в Spring Boot,
//*  которая указывает, что после каждого метода теста необходимо очищать контекст приложения.
//*  Это означает, что все бобы, созданные в контексте приложения, будут уничтожены после каждого теста, и при следующем тесте будет создан новый контекст приложения.
//
//*  Это полезно для обеспечения независимости тестов друг от друга.
//*  Если бы контекст приложения не очищался после каждого теста, то изменения, сделанные в контексте одним тестом,
//*  могли бы повлиять на другие тесты. Это могло бы привести к ошибкам и нестабильности тестов.
//* */
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class AuthorEntityRepositoryIntegrationTests {
//
//    private AuthorRepository underTest;
//
//    @Autowired
//    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest) {
//        this.underTest = underTest;
//    }
//
//    @Test
//    public void testThatAuthorCanBeCreatedAndRecalled() {
//
////        Создаём нового автора
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorEntity);
//        System.out.println(authorEntity);
//
//    }
//
////    Создаём нескольких авторов и тестируем
//    @Test
//    public void testThatMultipleAuthorsBeCreatedAndRecallerd() {
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        underTest.save(authorEntityA);
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        underTest.save(authorEntityB);
//
//        Iterable<AuthorEntity> result = underTest.findAll();
//        assertThat(result)
//                .hasSize(3)
//                .containsExactly(authorEntity, authorEntityA, authorEntityB);
//        System.out.println(result);
//    }
//
//    @Test
//    public void testThatAuthorCanBeUpdated() {
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        underTest.save(authorEntityB);
//        System.out.println(authorEntityB);
//        authorEntityB.setName("Солнышко");
//        underTest.save(authorEntityB);
//
//        Optional<AuthorEntity> result = underTest.findById(authorEntityB.getId());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(authorEntityB);
//        System.out.println(result);
//        System.out.println(authorEntityB);
//    }
//
//    @Test
//    public void testThatAuthorCanBeDeleted() {
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        underTest.deleteById(authorEntity.getId());
//
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        underTest.save(authorEntityA);
//
//        Iterable<AuthorEntity> results = underTest.findAll();
////        assertThat(result).isNotPresent();
////        assertThat(result.get()).isEqualTo(author);
//        System.out.println(results);
//    }
//
////    Создаём свой метод для поиска авторов по возрасту
//    @Test
//    public void testThatGetAuthorsWithAgeLessThan() {
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        authorEntityA.setAge(24);
//        underTest.save(authorEntityA);
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        authorEntityB.setAge(19);
//        underTest.save(authorEntityB);
//
//        Iterable<AuthorEntity> results = underTest.ageLessThan(22);
//        assertThat(results).containsExactly(authorEntity, authorEntityB);
//        System.out.println(results);
//
//    }
//
////    Метод поиска авторов по имени
//    @Test
//    public void testThatGetAuthorsWithSearchByName() {
//
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        authorEntityA.setName("Денис");
//        underTest.save(authorEntityA);
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        underTest.save(authorEntityB);
//
//        Iterable<AuthorEntity> result = underTest.searchByName("Денис");
//        assertThat(result).containsExactly(authorEntity, authorEntityA);
//        System.out.println(result);
//    }
//
////    Метод для поиска авторов у которых возраст больше определённого
//    @Test
//    public void testThatGetAuthorsWithAgeGreaterThan() {
//        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
//        underTest.save(authorEntity);
//        AuthorEntity authorEntityA = TestDataUtil.createTestAuthorA();
//        authorEntityA.setAge(24);
//        underTest.save(authorEntityA);
//        AuthorEntity authorEntityB = TestDataUtil.createTestAuthorB();
//        authorEntityB.setAge(19);
//        underTest.save(authorEntityB);
//
//        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThat(22);
//
//        assertThat(result).containsExactly(authorEntityA);
//    }
//
//}
