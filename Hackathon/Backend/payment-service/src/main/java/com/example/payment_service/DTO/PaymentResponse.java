package com.example.payment_service.DTO;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;

    public PaymentResponse(String status) {
        this.status = status;
    }
}
