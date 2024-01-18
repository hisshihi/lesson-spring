package com.lessonSpring.quickstar;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class JacksonTest {

//    Реализация того, как можно преобразовывать объекты java в json и обратно с помощью jackson
    @Test
    public void testThatObjectMapperCanCreateJsonFromJavaObject() {
        ObjectMapper objectMapper = new ObjectMapper();
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);

//        Преобразовываем объект в формат json
        try {
            String result = objectMapper.writeValueAsString(bookEntity);
            assertThat(result).isEqualTo("{\"isbn\":\"786932\",\"title\":\"The Lord of the pick\",\"author\":{\"id\":1,\"name\":\"Денис\",\"age\":21}}");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    Преобразовываем json в объект java
    @Test
    public void testThatObjectMapperCanCreateJavaObjectFromJsonObject() {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        BookEntity bookEntity = TestDataUtil.createTestBook(authorEntity);

//        Сначала создаём объект json
        String json = "{\"foo\":\"bar\", \"isbn\":\"786932\",\"title\":\"The Lord of the pick\",\"author\":{\"id\":1,\"name\":\"Денис\",\"age\":21}}";

//        Теперь создаём объект ObjectMapper
        final ObjectMapper objectMapper = new ObjectMapper();

//        Теперь преобразовываем объект json в класс Book java
        try {
            BookEntity result = objectMapper.readValue(json, BookEntity.class);
            assertThat(result).isEqualTo(bookEntity);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
