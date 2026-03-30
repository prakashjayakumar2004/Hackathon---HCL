package com.example.payment_service.Controller;

import com.example.payment_service.DTO.PaymentRequest;
import com.example.payment_service.DTO.PaymentResponse;
import com.example.payment_service.Entity.Payment;
import com.example.payment_service.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest request) {
        return service.processPayment(request);
    }

    @GetMapping("/{orderId}")
    public Payment get(@PathVariable Long orderId) {
        return service.getByOrderId(orderId);
    }
}