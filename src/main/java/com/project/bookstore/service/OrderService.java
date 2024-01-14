package com.project.bookstore.service;

import com.project.bookstore.model.dto.order.OrderItemResponseDto;
import com.project.bookstore.model.dto.order.OrderRequestDto;
import com.project.bookstore.model.dto.order.OrderResponseDto;
import com.project.bookstore.model.dto.order.OrderStatusRequestDto;
import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders(Long userId);

    OrderResponseDto createOrder(OrderRequestDto requestDto, Long userId);

    OrderResponseDto updateStatus(Long id, OrderStatusRequestDto requestDto);

    List<OrderItemResponseDto> getOrderItemsById(Long orderId, Long userId);

    OrderItemResponseDto getOrderItemById(Long orderId, Long itemId, Long userId);
}
