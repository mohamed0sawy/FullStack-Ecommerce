package com.ecommerce.FullStackEcommerce.controller;

import com.ecommerce.FullStackEcommerce.dto.Purchase;
import com.ecommerce.FullStackEcommerce.dto.PurchaseResponse;
import com.ecommerce.FullStackEcommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    @Autowired
    public CheckoutController(CheckoutService checkoutService){
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placing(@RequestBody Purchase purchase){
        return checkoutService.placeOrder(purchase);
    }
}
