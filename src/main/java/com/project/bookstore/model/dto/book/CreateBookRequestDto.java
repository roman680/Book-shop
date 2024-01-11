package com.project.bookstore.model.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    @ISBN
    private String isbn;
    @NotBlank
    @PositiveOrZero
    private BigDecimal price;
    @NotBlank
    @Length(max = 2000)
    private String description;
    @NotBlank
    private String coverImage;
    private Set<Long> categoryIds;
}
