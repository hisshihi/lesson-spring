package com.lessonSpring.quickstar.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lessonSpring.quickstar.TestDataUtil;
import com.lessonSpring.quickstar.domain.dto.BookDto;
import com.lessonSpring.quickstar.domain.entities.AuthorEntity;
import com.lessonSpring.quickstar.domain.entities.BookEntity;
import com.lessonSpring.quickstar.services.AuthorService;
import com.lessonSpring.quickstar.services.BookService;
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
public class BookControllerIntegrationTests {

    private MockMvc mockMvc;

    private BookService bookService;
    private AuthorService authorService;

    private ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTests(MockMvc mockMvc, BookService bookService, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.bookService = bookService;
        this.authorService = authorService;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {

        BookDto bookDto = TestDataUtil.createTestBook(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsSavedAuthor() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBook(null);
        String createBookJson = objectMapper.writeValueAsString(bookDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/books/" + bookDto.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );
    }

    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatListBooksReturnsListOfBooks() throws Exception {
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor();

        BookEntity bookEntity = TestDataUtil.createTestBookEntity(authorEntity);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn").value(bookEntity.getIsbn())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(bookEntity.getTitle())
        );
    }

    @Test
    public void testThatGetBookReturnsHttpStatus200OkWhenBookExist() throws Exception {
        BookEntity bookEntity = TestDataUtil.createTestBookEntity(null);
        bookService.createUpdateBook(bookEntity.getIsbn(), bookEntity);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + bookEntity.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatGetBookReturnsHttpStatus404WhenBookDoesntExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + "905902")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }




}
