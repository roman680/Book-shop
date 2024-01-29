package com.project.bookstore.model.dto.shoppingcrt;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddToCartRequestDto {
    private Long bookId;
    @Positive(message = "must be more than zero")
    private int quantity;
}
