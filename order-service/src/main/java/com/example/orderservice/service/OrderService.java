package com.example.orderservice.service;

import com.example.orderservice.kafka.KafkaMessageProducer;
import com.example.orderservice.model.Orders;
import com.example.orderservice.repository.OrderRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
	
	@Autowired
    private KafkaMessageProducer kafkaProducer;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Orders createOrders(Orders orders) {
        orders.setStatus("CREATED");
        Orders order = orderRepository.save(orders);
        
        JSONObject event = new JSONObject();
        event.put("orderId", order.getId());
        event.put("productId", order.getProductId());
        event.put("quantity", order.getQuantity());
        event.put("status", order.getStatus());

        kafkaProducer.sendMessage("order-events", event.toString());
        
        messagingTemplate.convertAndSend("/topic/orders", event.toString());        
        
        return order;
    }
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}