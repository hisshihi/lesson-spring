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
                .name("Денис")
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

    public static Book createTestBook(final Author author) {
        return Book.builder()
                .isbn("786932")
                .title("The Lord of the pick")
                .author(author)
                .build();
    }

    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("72971")
                .title("Harry Mommy")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("079867")
                .title("Story about the mysterious life")
                .author(author)
                .build();
    }

}
