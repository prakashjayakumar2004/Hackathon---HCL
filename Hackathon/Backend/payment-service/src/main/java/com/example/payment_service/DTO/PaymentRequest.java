package com.example.payment_service.DTO;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;
    private String upiId;
    private String upiPin;
    private double amount;

}
