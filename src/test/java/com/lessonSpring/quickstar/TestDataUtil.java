package com.lessonSpring.quickstar;

import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;

import java.util.List;

public final class TestDataUtil {

    private TestDataUtil() {

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Hiss")
                .age(21)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(2L)
                .name("HissDev")
                .age(21)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(3L)
                .name("Арина")
                .age(21)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("786932")
                .title("The Lord of the pick")
                .authorId(1L)
                .build();
    }
}
