package com.project.bookstore.model.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryRequestDto {
    @NotBlank(message = "Name should be not empty")
    private String name;
    private String description;
}
