package com.example.inventoryservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.inventoryservice.service.InventoryService;

@Component
public class KafkaListeners {
	
	@Autowired
	InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "inventory-service-group")
    public void listen(String message) {
        System.out.println("order-events received in inventory service. Message: " + message);
        
        inventoryService.handleOrderCreated(message);
        
    }
}
