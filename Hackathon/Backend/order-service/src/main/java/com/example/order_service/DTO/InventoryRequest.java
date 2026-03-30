package com.example.order_service.DTO;

import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;
    private int stock;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}