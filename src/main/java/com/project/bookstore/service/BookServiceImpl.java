package com.project.bookstore.service;

import com.project.bookstore.mapper.BookMapper;
import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.book.BookDto;
import com.project.bookstore.model.dto.book.BookDtoWithoutCategoryId;
import com.project.bookstore.model.dto.book.BookSearchParametersDto;
import com.project.bookstore.model.dto.book.CreateBookRequestDto;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.search.book.BookSpecificationBuilder;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        Book createdBook = bookRepository.save(book);
        return bookMapper.toDto(createdBook);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find book with id: " + id));
        return bookMapper.toDto(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can`t find book with id: " + id));

        book.setDeleted(true);

        bookRepository.save(book);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters) {
        return bookRepository.findAll(bookSpecificationBuilder.build(searchParameters)).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto updateBook(Long id, CreateBookRequestDto updateBookRequestDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Can`t find book with id:" + id);
        }
        Book book = bookMapper.toEntity(updateBookRequestDto);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toDto(updatedBook);
    }

    @Override
    public List<BookDtoWithoutCategoryId> getBooksByCategoryId(Long categoryId) {
        return bookRepository.findAllByCategoryId(categoryId).stream()
                .map(bookMapper::toBookDtoWithoutCategoryIds)
                .toList();
    }
}
