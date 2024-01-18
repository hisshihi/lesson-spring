package com.lessonSpring.quickstar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    //    Аннотация для автоматического подключения
    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    /*
     * Указываем путь
     * Указываем тип принимаемого контента
     * Указываем что принимаем
     * Условие по которому будет работать тест, а именно он будет успешным,
     * если статус будет равен "создано"
     * */
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
//        Создаём objectMapper для создания объекта json
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    //    Проверяем был ли создан автор
    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
//        Создаём objectMapper для создания объекта json
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorEntity.setId(null);
        String authorJson = objectMapper.writeValueAsString(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Денис")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(21)
        );
    }

}
