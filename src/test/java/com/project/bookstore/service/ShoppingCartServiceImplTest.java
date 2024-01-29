package com.project.bookstore.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.bookstore.mapper.ShoppingCartMapper;
import com.project.bookstore.model.Book;
import com.project.bookstore.model.CartItem;
import com.project.bookstore.model.Category;
import com.project.bookstore.model.ShoppingCart;
import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.shoppingcrt.AddToCartRequestDto;
import com.project.bookstore.model.dto.shoppingcrt.CartItemResponseDto;
import com.project.bookstore.model.dto.shoppingcrt.ShoppingCartResponseDto;
import com.project.bookstore.repository.BookRepository;
import com.project.bookstore.repository.CartItemRepository;
import com.project.bookstore.repository.ShoppingCartRepository;
import com.project.bookstore.repository.UserRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShoppingCartServiceImplTest {
    private static Book theGreatGatsbyBook;
    private static Category categoryOne;
    private static ShoppingCart shoppingCart;
    private static ShoppingCartResponseDto shoppingCartResponseDto;
    private static CartItem cartItem;
    private static CartItemResponseDto cartItemResponseDto;
    private static User user;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeAll
    static void beforeAll() {
        user = new User();
        user.setId(1L);

        categoryOne = new Category();
        categoryOne.setId(1L);
        categoryOne.setName("Novel");
        categoryOne.setDescription("Basic one");

        theGreatGatsbyBook = new Book();
        theGreatGatsbyBook.setId(1L);
        theGreatGatsbyBook.setTitle("The Great Gatsby");
        theGreatGatsbyBook.setAuthor("F. Scott Fitzgerald");
        theGreatGatsbyBook.setPrice(new BigDecimal("100.00"));
        theGreatGatsbyBook.setDescription("A novel about american dream");
        theGreatGatsbyBook.setCoverImage("the-great-gatsby.jpg");
        theGreatGatsbyBook.setCategories(Set.of(categoryOne));
        theGreatGatsbyBook.setIsbn("978-0-13-235088-4");

        cartItem = new CartItem();
        cartItem.setBook(theGreatGatsbyBook);
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setQuantity(1);
        cartItem.setId(1L);

        cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setId(1L);
        cartItemResponseDto.setQuantity(1);
        cartItemResponseDto.setBookId(theGreatGatsbyBook.getId());
        cartItemResponseDto.setBookTitle(theGreatGatsbyBook.getTitle());

        shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setId(1L);
        shoppingCart.setCartItems(Set.of(cartItem));

        shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(user.getId());
        shoppingCartResponseDto.setId(1L);
        shoppingCartResponseDto.setCartItems(Set.of(cartItemResponseDto));
    }

    @Test
    @DisplayName("Return shopping cart by user id")
    void getCartByUsedId_WithValidId_ReturnShoppingCartDto() {
        when(shoppingCartRepository.findByUserId(1L))
                .thenReturn(Optional.of(shoppingCart));
        when(shoppingCartMapper.toDto(shoppingCart))
                .thenReturn(shoppingCartResponseDto);

        ShoppingCartResponseDto expected = shoppingCartResponseDto;
        ShoppingCartResponseDto actual = shoppingCartService.getCartByUsedId(1L);

        assertEquals(expected, actual);
    }

    @Test
    public void testAddToCart() {
        AddToCartRequestDto requestDto = new AddToCartRequestDto();
        requestDto.setBookId(1L);
        requestDto.setQuantity(2);

        Book book = new Book();
        book.setId(1L);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(new User());

        CartItem cartItem = new CartItem();
        cartItem.setBook(book);
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setShoppingCart(shoppingCart);

        Long userId = 1L;

        ShoppingCartResponseDto expectedResponse = new ShoppingCartResponseDto();

        when(bookRepository.findById(requestDto.getBookId())).thenReturn(Optional.of(book));
        when(shoppingCartRepository.findByUserId(userId)).thenReturn(Optional.of(shoppingCart));
        when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);
        when(shoppingCartMapper.toDto(shoppingCart)).thenReturn(expectedResponse);

        ShoppingCartResponseDto response = shoppingCartService.addToCart(requestDto, userId);

        assertNotNull(response);
        assertEquals(expectedResponse, response);
        assertEquals(requestDto.getQuantity(), cartItem.getQuantity());
        assertEquals(book, cartItem.getBook());
        assertEquals(shoppingCart, cartItem.getShoppingCart());
        verify(bookRepository, times(1)).findById(requestDto.getBookId());
        verify(shoppingCartRepository, times(1)).findByUserId(userId);
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
        verify(shoppingCartMapper, times(1)).toDto(shoppingCart);
    }

    @Test
    public void testDeleteItem() {
        Long itemId = 1L;

        shoppingCartService.deleteItem(itemId);

        verify(cartItemRepository, times(1)).deleteById(itemId);
    }
}
