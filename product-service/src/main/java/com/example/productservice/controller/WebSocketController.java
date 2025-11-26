package com.example.productservice.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Controller
public class WebSocketController {

    @MessageMapping("/hello") // client sends message to /app/hello
    @SendTo("/topic/greetings") // server broadcasts to /topic/greetings
    public String greet(String message) {
        return "Hello, " + message + "!";
    }
}
