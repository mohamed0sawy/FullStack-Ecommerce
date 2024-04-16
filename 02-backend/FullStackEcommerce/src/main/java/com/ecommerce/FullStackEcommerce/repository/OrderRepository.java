package com.ecommerce.FullStackEcommerce.repository;

import com.ecommerce.FullStackEcommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
