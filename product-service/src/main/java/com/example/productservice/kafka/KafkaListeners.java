package com.example.productservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "order-events", groupId = "product-service-group")
    public void listen(String message) {
        System.out.println("order-events received in product service. Message: " + message);
    }
}
