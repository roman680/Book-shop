package com.project.bookstore.service;

import com.project.bookstore.mapper.OrderItemMapper;
import com.project.bookstore.mapper.OrderMapper;
import com.project.bookstore.model.CartItem;
import com.project.bookstore.model.Order;
import com.project.bookstore.model.OrderItem;
import com.project.bookstore.model.ShoppingCart;
import com.project.bookstore.model.User;
import com.project.bookstore.model.dto.order.OrderItemResponseDto;
import com.project.bookstore.model.dto.order.OrderRequestDto;
import com.project.bookstore.model.dto.order.OrderResponseDto;
import com.project.bookstore.model.dto.order.OrderStatusRequestDto;
import com.project.bookstore.repository.CartItemRepository;
import com.project.bookstore.repository.OrderItemRepository;
import com.project.bookstore.repository.OrderRepository;
import com.project.bookstore.repository.ShoppingCartRepository;
import com.project.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderResponseDto> getAllOrders(Long userId) {
        List<Order> ordersFromDB = orderRepository.findAllByUserId(userId);
        return ordersFromDB.stream()
                .map(orderMapper::toResponseDto).toList();
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto requestDto, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can`t find user by id:" + userId));
        ShoppingCart shoppingCartFromDB = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Can`t find cart by user with id:" + user.getId()));
        Order order = new Order();
        BigDecimal total = shoppingCartFromDB.getCartItems().stream()
                .map(c -> c.getBook().getPrice().multiply(BigDecimal.valueOf(c.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setStatus(Order.Status.PENDING);
        order.setUser(user);
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setTotal(total);
        orderRepository.save(order);
        Set<CartItem> cartItems = shoppingCartFromDB.getCartItems();
        cartItemRepository.deleteAll(shoppingCartFromDB.getCartItems());
        setOrderItems(cartItems, order);
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    @Override
    @Transactional
    public OrderResponseDto updateStatus(Long id, OrderStatusRequestDto requestDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can`t find order by id:" + id));
        order.setStatus(requestDto.status());
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    @Override
    public List<OrderItemResponseDto> getOrderItemsById(Long orderId, Long userId) {
        return orderItemRepository.findAllByOrderId(orderId, userId).stream()
                .map(orderItemMapper::toResponseDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto getOrderItemById(Long orderId, Long itemId, Long userId) {
        return orderItemMapper
                .toResponseDto(orderItemRepository.findByOrderIdAndId(orderId, itemId, userId));
    }

    private Set<OrderItem> setOrderItems(Set<CartItem> cartItems, Order order) {
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(cartItem.getBook());
            orderItem.setPrice(cartItem.getBook().getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity())));
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        return orderItems;
    }
}
