package com.example.inventoryservice.service;

import com.example.common_lib.kafka.events.OrderCreatedEvent;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository repository;
    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }
    public boolean reserveProduct(String productId, int qty) {
        Optional<InventoryItem> itemOpt = repository.findById(productId);
        if (itemOpt.isPresent() && itemOpt.get().getAvailableQty() >= qty) {
            InventoryItem item = itemOpt.get();
            item.setAvailableQty(item.getAvailableQty() - qty);
            repository.save(item);
            return true;
        }
        return false;
    }
    
    public void handleOrderCreated(OrderCreatedEvent event) {
       System.out.println("handleOrderCreated in InventoryService");
    }
    
}