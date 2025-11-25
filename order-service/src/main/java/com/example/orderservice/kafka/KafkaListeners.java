package com.example.orderservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "order-events", groupId = "order-service-group")
    public void listen(String message) {
        System.out.println("order-events received in order service. Message: " + message);
    }
}
