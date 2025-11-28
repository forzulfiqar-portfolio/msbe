package com.example.orderservice.service;

import com.example.orderservice.kafka.KafkaProducer;
import com.example.common_lib.kafka.events.OrderCreatedEvent;
import com.example.orderservice.model.Orders;
import com.example.orderservice.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private KafkaProducer kafkaProducer;

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrders(Orders orders) {

        // Business logic
        orders.setStatus("CREATED");
        Orders savedOrder = orderRepository.save(orders);

        OrderCreatedEvent event = new OrderCreatedEvent(
        		savedOrder.getId(),
        		savedOrder.getProductId(),
        		savedOrder.getQuantity(),
        		savedOrder.getStatus()
        );

        kafkaProducer.send("order-events", event);

        return savedOrder;
    }

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}
