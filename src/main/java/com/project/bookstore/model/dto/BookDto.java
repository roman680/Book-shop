package com.project.bookstore.model.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private double price;
    private String description;
    private String coverImage;
}
