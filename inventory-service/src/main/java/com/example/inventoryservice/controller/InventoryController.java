package com.example.inventoryservice.controller;

import com.example.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService service;
    public InventoryController(InventoryService service) {
        this.service = service;
    }
    @PostMapping("/reserve")
    public boolean reserve(@RequestParam String productId, @RequestParam int qty) {
        return service.reserveProduct(productId, qty);
    }
}