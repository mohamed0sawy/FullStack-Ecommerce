package com.ecommerce.FullStackEcommerce.service;

import com.ecommerce.FullStackEcommerce.dto.Purchase;
import com.ecommerce.FullStackEcommerce.dto.PurchaseResponse;
import com.ecommerce.FullStackEcommerce.entity.Address;
import com.ecommerce.FullStackEcommerce.entity.Customer;
import com.ecommerce.FullStackEcommerce.entity.Order;
import com.ecommerce.FullStackEcommerce.entity.OrderItem;
import com.ecommerce.FullStackEcommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        String orderTrackingNumber = generateOrderTrackingNumber();
        Set<OrderItem> orderItems = purchase.getOrderItems();
        Address shippingAddress = purchase.getShippingAddress();
        Address billingAddress = purchase.getBillingAddress();
        Customer customer = purchase.getCustomer();

        order.setOrderTrackingNumber(orderTrackingNumber);
        orderItems.forEach(order::add);
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        customer.add(order);
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}
