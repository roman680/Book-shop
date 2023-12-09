package com.project.bookstore.mapper;

import com.project.bookstore.config.MapperConfig;
import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(CreateBookRequestDto createBookRequestDto);
}
