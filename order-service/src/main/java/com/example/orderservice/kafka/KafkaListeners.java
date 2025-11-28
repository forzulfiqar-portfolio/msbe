package com.example.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.orderservice.kafka.events.OrderCreatedEvent;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "order-events",
            groupId = "order-service-group",
            containerFactory = "orderEventListenerFactory"
    )
    public void listen(OrderCreatedEvent event) {
        System.out.println("order-events received: " + event);
    }
}
