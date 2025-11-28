package com.example.orderservice.service;

import com.example.orderservice.kafka.KafkaMessageProducer;
import com.example.orderservice.kafka.events.OrderCreatedEvent;
import com.example.orderservice.model.Orders;
import com.example.orderservice.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private KafkaMessageProducer kafkaProducer;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrders(Orders orders) {

        // Business logic
        orders.setStatus("CREATED");
        Orders savedOrder = orderRepository.save(orders);

        // Build event object (JSON-safe)
        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),            // Long
                savedOrder.getProductId(),     // Long
                savedOrder.getQuantity(),      // int
                savedOrder.getStatus()         // String
        );

        // Publish event to Kafka
        kafkaProducer.sendOrderCreatedEvent(event);

        // DO NOT send WebSocket updates from microservices anymore
        // WebSockets will be moved to Notification-Service

        return savedOrder;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
