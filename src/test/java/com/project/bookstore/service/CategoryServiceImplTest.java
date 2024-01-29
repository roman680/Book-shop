package com.project.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.bookstore.mapper.CategoryMapper;
import com.project.bookstore.model.Category;
import com.project.bookstore.model.dto.category.CategoryDto;
import com.project.bookstore.model.dto.category.CategoryRequestDto;
import com.project.bookstore.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDto categoryDto;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test Category");
        category.setDescription("Test Description");

        categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
    }

    @Test
    void getAll_RetrieveCategories_ReturnsListOfCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        when(categoryRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(categories));

        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        List<CategoryDto> result = categoryService.getAll(PageRequest.of(0, 10));

        assertEquals(1, result.size());
        assertEquals(categoryDto, result.get(0));
    }

    @Test
    void findById_CategoryExists_ReturnsCategoryDto() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.findById(1L);

        assertEquals(categoryDto, result);
    }

    @Test
    void findById_CategoryNotExists_ThrowsEntityNotFoundException() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> categoryService.findById(2L));
    }

    @Test
    void save_NewCategory_ReturnsSavedCategoryDto() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("New Category");
        requestDto.setDescription("New Description");

        when(categoryMapper.toModel(requestDto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.save(requestDto);

        assertEquals(categoryDto, result);
    }

    @Test
    void updateById_ValidId_UpdatesCategoryAndReturnsDto() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setName("Updated Category");
        requestDto.setDescription("Updated Description");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(categoryDto);

        CategoryDto result = categoryService.updateById(1L, requestDto);

        assertEquals(categoryDto, result);
        assertEquals(requestDto.getName(), category.getName());
        assertEquals(requestDto.getDescription(), category.getDescription());
    }

    @Test
    void updateById_InvalidId_ThrowsEntityNotFoundException() {
        CategoryRequestDto requestDto = new CategoryRequestDto();

        when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> categoryService.updateById(2L, requestDto));
    }

    @Test
    void deleteById_ValidId_DeletesCategory() {
        categoryService.deleteById(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}
