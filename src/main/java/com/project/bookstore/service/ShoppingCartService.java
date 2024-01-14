package com.project.bookstore.service;

import com.project.bookstore.model.dto.shoppingcrt.AddToCartRequestDto;
import com.project.bookstore.model.dto.shoppingcrt.CartItemsUpdateDto;
import com.project.bookstore.model.dto.shoppingcrt.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartResponseDto getCartByUsedId(Long id);

    void deleteItem(Long id);

    ShoppingCartResponseDto updateCartItem(Long cartItemId, CartItemsUpdateDto updateDto, Long id);
}
