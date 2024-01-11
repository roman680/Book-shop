package com.project.bookstore.service;

import com.project.bookstore.model.dto.book.BookDto;
import com.project.bookstore.model.dto.book.BookDtoWithoutCategoryId;
import com.project.bookstore.model.dto.book.BookSearchParametersDto;
import com.project.bookstore.model.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(CreateBookRequestDto bookDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto updateBook(Long id, CreateBookRequestDto updateBookRequestDto);

    void deleteBook(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);

    List<BookDtoWithoutCategoryId> getBooksByCategoryId(Long categoryId);
}
