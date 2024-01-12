package com.project.bookstore.service;

import com.project.bookstore.model.dto.category.CategoryDto;
import com.project.bookstore.model.dto.category.CategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> getAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CategoryRequestDto requestDto);

    CategoryDto updateById(Long id, CategoryRequestDto requestDto);

    void deleteById(Long id);
}
