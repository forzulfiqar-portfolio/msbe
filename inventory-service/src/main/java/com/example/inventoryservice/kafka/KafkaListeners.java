package com.example.inventoryservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.common_lib.kafka.events.OrderCreatedEvent;
import com.example.inventoryservice.service.InventoryService;

@Component
public class KafkaListeners {
	
	@Autowired
	InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(OrderCreatedEvent event) {
		System.out.println("📥 Received order event: " + event);
		inventoryService.handleOrderCreated(event);
	}    
}
