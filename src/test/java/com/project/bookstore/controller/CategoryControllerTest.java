package com.project.bookstore.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.bookstore.model.dto.book.BookDtoWithoutCategoryId;
import com.project.bookstore.model.dto.category.CategoryDto;
import com.project.bookstore.model.dto.category.CategoryRequestDto;
import com.project.bookstore.service.BookService;
import com.project.bookstore.service.CategoryService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void getAll_ReturnsAllCategories_Success() {
        List<CategoryDto> categories = Collections.emptyList();
        when(categoryService.getAll(any(Pageable.class))).thenReturn(categories);
        assertEquals(categories, categoryController.getAll(PageRequest.of(0, 10)));
    }

    @Test
    void findById_GivenValidCategoryId_ReturnsCategoryDto() {
        long categoryId = 1L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);

        when(categoryService.findById(categoryId)).thenReturn(categoryDto);

        assertEquals(categoryDto, categoryController.findById(categoryId));
    }

    @Test
    void save_GivenCategoryRequestDto_ReturnsSavedCategoryDto() {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        CategoryDto savedDto = new CategoryDto();

        when(categoryService.save(requestDto)).thenReturn(savedDto);

        assertEquals(savedDto, categoryController.save(requestDto));
    }

    @Test
    void update_GivenCategoryIdAndRequestDto_ReturnsUpdatedCategoryDto() {
        long categoryId = 1L;
        CategoryRequestDto requestDto = new CategoryRequestDto();
        CategoryDto updatedDto = new CategoryDto();

        when(categoryService.updateById(categoryId, requestDto)).thenReturn(updatedDto);

        assertEquals(updatedDto, categoryController.update(categoryId, requestDto));
    }

    @Test
    void deleteById_GivenValidCategoryId_DoesNotThrowException() {
        long categoryId = 1L;

        assertDoesNotThrow(() -> categoryController.deleteById(categoryId));

        verify(categoryService, times(1)).deleteById(categoryId);
    }

    @Test
    void getBooksByCategoryId_GivenValidCategoryId_ReturnsListOfBooks() {
        long categoryId = 1L;
        List<BookDtoWithoutCategoryId> books = Collections.emptyList();

        when(bookService.getBooksByCategoryId(categoryId)).thenReturn(books);

        assertEquals(books, categoryController.getBooksByCategoryId(categoryId));
    }
}
