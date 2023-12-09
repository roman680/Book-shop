package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import com.project.bookstore.model.dto.UpdateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);

    Book delete(Long id);

    BookDto update(Long id, UpdateBookRequestDto requestDto);
}
