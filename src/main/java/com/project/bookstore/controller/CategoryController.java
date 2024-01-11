package com.project.bookstore.controller;

import com.project.bookstore.model.dto.book.BookDtoWithoutCategoryId;
import com.project.bookstore.model.dto.category.CategoryDto;
import com.project.bookstore.model.dto.category.CategoryRequestDto;
import com.project.bookstore.service.BookService;
import com.project.bookstore.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Categories management", description = "Endpoints for managing categories")
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Retrieve all categories",
            description = "Retrieve a pageable list of all the categories")
    @GetMapping
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryService.getAll(pageable);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Retrieve category by ID",
            description = "Retrieve category by ID from DB")
    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a new category",
            description = "Add a new category to DB")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryDto save(@RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update category by ID",
            description = "Update category details by ID")
    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody @Valid CategoryRequestDto requestDto) {
        return categoryService.updateById(id, requestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete category by ID",
            description = "Remove category from the DB by ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Retrieve books from a certain category",
            description = "Retrieve all the books of a certain category using its ID")
    @GetMapping("/{id}/books")
    public List<BookDtoWithoutCategoryId> getBooksByCategoryId(
            @PathVariable(name = "id") Long categoryId
    ) {
        return bookService.getBooksByCategoryId(categoryId);
    }
}
