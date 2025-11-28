package com.example.paymentservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.common_lib.kafka.events.OrderCreatedEvent;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "order-events", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(OrderCreatedEvent event) {
		System.out.println("📥 Received order event: " + event);		
	}
}
