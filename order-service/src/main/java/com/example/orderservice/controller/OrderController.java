package com.example.orderservice.controller;

import com.example.orderservice.model.Orders;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service) {
        this.service = service;
    }
    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return service.createOrders(order);
    }
    @GetMapping
    public List<Orders> getOrders() {
        return service.getAllOrders();
    }
}