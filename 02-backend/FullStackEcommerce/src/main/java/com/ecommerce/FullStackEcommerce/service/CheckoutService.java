package com.ecommerce.FullStackEcommerce.service;

import com.ecommerce.FullStackEcommerce.dto.Purchase;
import com.ecommerce.FullStackEcommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
