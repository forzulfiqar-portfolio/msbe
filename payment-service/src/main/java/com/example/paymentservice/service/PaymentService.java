package com.example.paymentservice.service;

import com.example.paymentservice.model.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }
    public Payment processPayment(Payment payment) {
        payment.setStatus("SUCCESS");
        return repository.save(payment);
    }
}