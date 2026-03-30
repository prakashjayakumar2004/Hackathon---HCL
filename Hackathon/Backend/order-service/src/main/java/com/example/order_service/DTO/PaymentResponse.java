package com.example.order_service.DTO;

import lombok.Data;

@Data
public class PaymentResponse {
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}