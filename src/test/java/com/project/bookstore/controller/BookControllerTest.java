package com.project.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.bookstore.model.dto.book.BookDto;
import com.project.bookstore.model.dto.book.CreateBookRequestDto;
import com.project.bookstore.service.BookService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        List<BookDto> books = Arrays.asList(new BookDto(), new BookDto());
        when(bookService.findAll(any(Pageable.class))).thenReturn(books);

        List<BookDto> result = bookController.getAll(mock(Pageable.class));

        assertEquals(2, result.size());
        verify(bookService, times(1)).findAll(any(Pageable.class));
    }

    @Test
    void testGetBookById() {
        long bookId = 1L;
        BookDto bookDto = new BookDto();
        when(bookService.getBookById(bookId)).thenReturn(bookDto);

        BookDto result = bookController.getBookById(bookId);

        assertEquals(bookDto, result);
        verify(bookService, times(1)).getBookById(bookId);
    }

    @Test
    void testCreateBook() {
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        BookDto bookDto = new BookDto();
        when(bookService.save(createBookRequestDto)).thenReturn(bookDto);

        BookDto result = bookController.createBook(createBookRequestDto);

        assertEquals(bookDto, result);
        verify(bookService, times(1)).save(createBookRequestDto);
    }

    @Test
    void testUpdateBook() {
        long bookId = 1L;
        CreateBookRequestDto createBookRequestDto = new CreateBookRequestDto();
        BookDto bookDto = new BookDto();
        when(bookService.updateBook(bookId, createBookRequestDto)).thenReturn(bookDto);

        BookDto result = bookController.updateBook(bookId, createBookRequestDto);

        assertEquals(bookDto, result);
        verify(bookService, times(1)).updateBook(bookId, createBookRequestDto);
    }

    @Test
    void testDeleteBook() {
        long bookId = 1L;
        ResponseEntity<Void> expectedResponse = ResponseEntity.noContent().build();

        ResponseEntity<Void> result = bookController.deleteBook(bookId);

        assertEquals(expectedResponse, result);
        verify(bookService, times(1)).deleteBook(bookId);
    }
}
