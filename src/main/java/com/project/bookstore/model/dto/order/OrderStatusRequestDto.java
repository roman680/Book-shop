package com.project.bookstore.model.dto.order;

import com.project.bookstore.model.Order;
import jakarta.validation.constraints.NotNull;

public record OrderStatusRequestDto(@NotNull Order.Status status) {
}
