package com.ecommerce.FullStackEcommerce.repository;

import com.ecommerce.FullStackEcommerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
