package com.ecommerce.FullStackEcommerce.repository;

import com.ecommerce.FullStackEcommerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
