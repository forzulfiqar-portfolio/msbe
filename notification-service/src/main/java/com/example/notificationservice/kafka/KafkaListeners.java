package com.example.notificationservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.common_lib.kafka.events.OrderCreatedEvent;

@Component
public class KafkaListeners {
		
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
       
    @KafkaListener(
    	    topics = "order-events",
    	    groupId = "${spring.kafka.consumer.group-id}",
    	    containerFactory = "kafkaListenerFactory"
    	)
    	public void consumeOrderEvents(OrderCreatedEvent event) {
    	    messagingTemplate.convertAndSend("/topic/orders", event);
    	}

    
    
}
