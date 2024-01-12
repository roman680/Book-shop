package com.project.bookstore.mapper;

import com.project.bookstore.model.Category;
import com.project.bookstore.model.dto.category.CategoryDto;
import com.project.bookstore.model.dto.category.CategoryRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CategoryRequestDto categoryDto);
}
