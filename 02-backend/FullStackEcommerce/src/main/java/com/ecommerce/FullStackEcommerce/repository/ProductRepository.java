package com.ecommerce.FullStackEcommerce.repository;

import com.ecommerce.FullStackEcommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
