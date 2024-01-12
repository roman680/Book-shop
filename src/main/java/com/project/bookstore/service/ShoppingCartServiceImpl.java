package com.project.bookstore.service;

import com.project.bookstore.mapper.ShoppingCartMapper;
import com.project.bookstore.model.Book;
import com.project.bookstore.model.CartItem;
import com.project.bookstore.model.ShoppingCart;
import com.project.bookstore.model.dto.shoppingcrt.AddToCartRequestDto;
import com.project.bookstore.model.dto.shoppingcrt.CartItemsUpdateDto;
import com.project.bookstore.model.dto.shoppingcrt.ShoppingCartResponseDto;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.CartItemRepository;
import com.project.bookstore.repository.ShoppingCartRepository;
import com.project.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponseDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        CartItem cartItem = new CartItem();
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t find book by ID: " + requestDto.getBookId()));

        ShoppingCart shoppingCartFromDB = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(userRepository.findById(userId).orElseThrow(
                            () -> new EntityNotFoundException("Can`t find user by ID: ")
                    ));
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDB);
        Set<CartItem> set = new HashSet<>();
        cartItemRepository.save(cartItem);
        shoppingCartFromDB.setCartItems(set);
        shoppingCartFromDB.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCartFromDB);
    }

    @Override
    public ShoppingCartResponseDto getCartByUsedId(Long id) {
        ShoppingCart shoppingCartFromDB = shoppingCartRepository.findByUserId(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can`t find cart for the user with id:"
                                + id));
        return shoppingCartMapper.toDto(shoppingCartFromDB);
    }

    @Override
    public void deleteItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public ShoppingCartResponseDto updateCartItem(
            Long cartItemId,
            CartItemsUpdateDto updateDto,
            Long userId
    ) {
        CartItem cartItemFromDB = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t find cart item by ID:" + cartItemId));
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setQuantity(updateDto.getQuantity());
        cartItem.setShoppingCart(cartItemFromDB.getShoppingCart());
        cartItem.setBook(cartItemFromDB.getBook());
        cartItemRepository.save(cartItem);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can`t find cart item by ID:" + userId));
        shoppingCart.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }
}
