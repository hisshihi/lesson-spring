package com.lessonSpring.quickstar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessonSpring.quickstar.domain.Author;
import com.lessonSpring.quickstar.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class JacksonTest {

//    Реализация того, как можно преобразовывать объекты java в json и обратно с помощью jackson
    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() {
        ObjectMapper objectMapper = new ObjectMapper();
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);

//        Преобразовываем объект в формат json
        try {
            String result = objectMapper.writeValueAsString(book);
            assertThat(result).isEqualTo("{\"isbn\":\"786932\",\"title\":\"The Lord of the pick\",\"author\":{\"id\":1,\"name\":\"Денис\",\"age\":21}}");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    Преобразовываем json в объект java
    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() {
        Author author = TestDataUtil.createTestAuthor();
        Book book = TestDataUtil.createTestBook(author);

//        Сначала создаём объект json
        String json = "{\"foo\":\"bar\", \"isbn\":\"786932\",\"title\":\"The Lord of the pick\",\"author\":{\"id\":1,\"name\":\"Денис\",\"age\":21}}";

//        Теперь создаём объект ObjectMapper
        final ObjectMapper objectMapper = new ObjectMapper();

//        Теперь преобразовываем объект json в класс Book java
        try {
            Book result = objectMapper.readValue(json, Book.class);
            assertThat(result).isEqualTo(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
