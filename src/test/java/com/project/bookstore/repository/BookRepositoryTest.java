package com.project.bookstore.repository;

import static org.junit.Assert.assertEquals;

import com.project.bookstore.model.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {
        "classpath:database/book/insert-book.sql",
        "classpath:database/categories/insert-categories.sql",
        "classpath:database/categories/insert-into-books-categories-table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        "classpath:database/book/delete-book.sql"
}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@DataJpaTest
class BookRepositoryTest {
    private static List<Book> expectedList;
    private static Book book1;
    private static Book book2;

    @Autowired
    private BookRepository bookRepository;

    @BeforeAll
    static void beforeAll() {
        expectedList = new ArrayList<>();
        book1 = new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", "978-0-13-235088-4",
                BigDecimal.valueOf(100), "A novel about american dream", "the-great-gatsby.jpg",
                false);
        book2 = new Book(2L, "Vanity Fair", "William Thackeray", "978-0-19-953762-4",
                BigDecimal.valueOf(90), "A classic novel of social satire", "vanity-fair.jpg",
                false);
        expectedList.add(book1);
        expectedList.add(book2);
    }

    @Test
    void findAllByCategoryId() {
        List<Book> actual = bookRepository.findAllByCategoryId(1L);
        assertEquals(expectedList.size(), actual.size());
    }

    @Test
    void findById() {
        Optional<Book> actual = bookRepository.findById(1L);
        assertEquals(expectedList.get(0), actual.get());
    }
}
