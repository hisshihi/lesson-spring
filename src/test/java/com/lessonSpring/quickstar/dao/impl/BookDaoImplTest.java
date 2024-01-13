package com.lessonSpring.quickstar.dao.impl;

import com.lessonSpring.quickstar.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static com.lessonSpring.quickstar.TestDataUtil.createTestBook;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl bookDao;

//    Добавляем данные в бд
    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Book book = createTestBook();

        bookDao.create(book);

        verify(jdbcTemplate).update(eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("8-934-321-00-34"), eq("The Lord of the pick"), eq(1L)
        );
    }


    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        bookDao.findOne("8-934-321-00-34");
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("8-934-321-00-34")
                );
    }

}
