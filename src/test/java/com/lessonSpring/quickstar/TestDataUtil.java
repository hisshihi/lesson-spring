package com.lessonSpring.quickstar;

import com.lessonSpring.quickstar.domain.dto.AuthorDto;
import com.lessonSpring.quickstar.domain.dto.BookDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import com.lessonSpring.quickstar.domain.entities.PostEntity;

import java.time.LocalDateTime;

public final class TestDataUtil {

    private TestDataUtil() {

    }

    public static AuthorEntity createTestAuthor() {
        return AuthorEntity.builder()
                .id(1L)
                .name("Денис")
                .age(21)
                .build();
    }

    public static AuthorEntity createTestAuthorA() {
        return AuthorEntity.builder()
                .id(2L)
                .name("HissDev")
                .age(21)
                .build();
    }

    public static AuthorEntity createTestAuthorB() {
        return AuthorEntity.builder()
                .id(3L)
                .name("Арина")
                .age(21)
                .build();
    }

    public static AuthorDto createTestAuthorDto() {
        return AuthorDto.builder()
                .id(4L)
                .name("Иван")
                .age(20)
                .build();
    }

    public static BookEntity createTestBookEntity(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("786932")
                .title("The Lord of the pick")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDto createTestBook(final AuthorDto author) {
        return BookDto.builder()
                .isbn("786932")
                .title("The Lord of the pick")
                .authorDto(author)
                .build();
    }

    public static BookEntity createTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("72971")
                .title("Harry Mommy")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("079867")
                .title("Story about the mysterious life")
                .authorEntity(authorEntity)
                .build();
    }

    public static PostEntity createPostA() {
        return PostEntity.builder()
                .id(1L)
                .title("Первый пост")
                .text("Мой первый пост")
                .time(LocalDateTime.now())
                .build();
    }

}
