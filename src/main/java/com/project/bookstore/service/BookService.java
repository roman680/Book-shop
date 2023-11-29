package com.project.bookstore.service;

import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {

    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll();

    BookDto getBookById(Long id);
}
