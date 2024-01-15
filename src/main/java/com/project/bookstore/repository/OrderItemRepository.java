package com.project.bookstore.repository;

import com.project.bookstore.model.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("""
        select o
        from OrderItem o
        where o.order.id = :orderId and o.order.user.id = :userId
            """)
    List<OrderItem> findAllByOrderId(Long orderId, Long userId);

    @Query("""
        select o
        from OrderItem o
        where o.order.id = :orderId and o.order.user.id = :userId and o.id = :itemId
            """)
    OrderItem findByOrderIdAndId(Long orderId, Long itemId, Long userId);
}
