package com.lessonSpring.quickstar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.dto.AuthorDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.services.AuthorService;
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

    private AuthorService authorService;

    private ObjectMapper objectMapper;

    //    Аннотация для автоматического подключения
    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.authorService = authorService;
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

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Денис")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(21)
        );
    }

    @Test
    public void testThatListAuthorsNameReturnsHttpStatus200() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/name/" + authorEntity.getName())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthorsByName() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/name/" + authorEntity.getName())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Денис")
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(21)
        );
    }

    @Test
    public void testThatGetAuthorsReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetAuthorsReturnsHttpStatus404WhenAuthorExist() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatGetAuthorsReturnsAuthorsById() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        authorService.save(authorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Денис")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.age").value(21)
        );
    }

    //    Тест для првоерки отправки кода 404 при полном обновлении автора
    @Test
    public void testThatFullUpdateAuthorsReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        AuthorDto authorDto = TestDataUtil.createTestAuthorDto();
        String authorDtoJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/update/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    //    Тест для проверки отправки кода 200 при полном обновлении автора
    @Test
    public void testThatFullUpdateAuthorsReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorEntity = TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorEntity);

        AuthorDto authorDto = TestDataUtil.createTestAuthorDto();
        String authorDtoJson = objectMapper.writeValueAsString(authorDto);

        System.out.println(savedAuthorEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/update/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    //    Проверяем, точн ли обновляется атвор
    @Test
    public void testThatFullUpdateUpdatesExistingAuthor() throws Exception {
//        Создаём тестового автора
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();
        AuthorEntity savedAuthorEntity = authorService.save(authorEntity);

        AuthorEntity authorDto = TestDataUtil.createTestAuthorA();
        authorDto.setId(savedAuthorEntity.getId());

        String authorJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/update/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge()));
    }

}
