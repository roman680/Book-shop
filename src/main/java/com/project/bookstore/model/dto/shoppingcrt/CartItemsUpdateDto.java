package com.project.bookstore.model.dto.shoppingcrt;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemsUpdateDto {
    @Positive(message = "must be positive")
    private int quantity;
}
