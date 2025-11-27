package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallback/products")
    public String productFallback() {
        return "Product Service temporarily unavailable. Please try again.";
    }

    @GetMapping("/fallback/orders")
    public String orderFallback() {
        return "Order Service temporarily unavailable. Please try again.";
    }

    @GetMapping("/fallback/payments")
    public String paymentFallback() {
        return "Payment Service temporarily unavailable.";
    }

    @GetMapping("/fallback/inventory")
    public String inventoryFallback() {
        return "Inventory Service temporarily unavailable.";
    }
}
