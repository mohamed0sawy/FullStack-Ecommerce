package com.ecommerce.FullStackEcommerce.dto;

import com.ecommerce.FullStackEcommerce.entity.Address;
import com.ecommerce.FullStackEcommerce.entity.Customer;
import com.ecommerce.FullStackEcommerce.entity.Order;
import com.ecommerce.FullStackEcommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
