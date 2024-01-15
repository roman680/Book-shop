package com.project.bookstore.repository;

import com.project.bookstore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
    select o
    from Order o
    join fetch o.user
    join fetch o.orderItems
            """)
    List<Order> findAllByUserId(Long id);
}
