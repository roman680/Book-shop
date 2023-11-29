package com.project.bookstore.mapper;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.dto.BookDto;
import com.project.bookstore.model.dto.CreateBookRequestDto;
import config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto bookToBookDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book createBookRequestDtoToBook(CreateBookRequestDto createBookRequestDto);
}
