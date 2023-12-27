package com.project.bookstore.mapper;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toEntity(CreateBookRequestDto createBookRequestDto);
}
