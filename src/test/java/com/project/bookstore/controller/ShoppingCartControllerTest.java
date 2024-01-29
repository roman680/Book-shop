package com.project.bookstore.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.shoppingcrt.AddToCartRequestDto;
import com.project.bookstore.model.dto.shoppingcrt.ShoppingCartResponseDto;
import com.project.bookstore.service.ShoppingCartService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ShoppingCartControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ShoppingCartService shoppingCartService;

    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(shoppingCartController).build();
    }

    @Test
    void testAddToCart() throws Exception {
        User user = new User();

        AddToCartRequestDto requestDto = new AddToCartRequestDto();
        requestDto.setBookId(1L);
        requestDto.setQuantity(2);

        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(user.getId());

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);

        when(shoppingCartService.addToCart(any(AddToCartRequestDto.class), anyLong()))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"bookId\": 1, \"quantity\": 2 }")
                        .principal(authentication))
                .andExpect(status().isCreated());
    }

    @Test
    void getCart_ReturnsCartResponseDto() {
        Authentication authentication = createMockAuthentication();

        ShoppingCartResponseDto expectedResponseDto = new ShoppingCartResponseDto();
        expectedResponseDto.setCartItems(Collections.emptySet());

        when(shoppingCartService.getCartByUsedId(any(Long.class))).thenReturn(expectedResponseDto);

        ShoppingCartResponseDto responseDto = shoppingCartController.getCart(authentication);

        assertEquals(expectedResponseDto, responseDto);
    }

    private Authentication createMockAuthentication() {
        User user = new User();
        user.setId(1L);
        // Set other properties of the user as needed

        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);

        return authentication;
    }

    @Test
    void deleteItem_ReturnsNoContent() throws Exception {
        // Arrange
        Long cartItemId = 1L;

        // Act
        mockMvc.perform(delete("/api/cart/cart-items/{cartItemId}", cartItemId))
                .andExpect(status().isNoContent());

        // Assert
        verify(shoppingCartService, times(1)).deleteItem(cartItemId);
    }
}
