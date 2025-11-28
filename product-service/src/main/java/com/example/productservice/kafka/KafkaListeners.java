package com.example.productservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.common_lib.kafka.events.OrderCreatedEvent;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "order-events", groupId = "product-service-group")
    public void listen(OrderCreatedEvent event) {
		System.out.println("📥 Received order event: " + event);	
	}
}
