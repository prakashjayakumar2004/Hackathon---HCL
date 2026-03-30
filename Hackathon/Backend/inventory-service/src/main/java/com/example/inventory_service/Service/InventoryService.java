package com.example.inventory_service.Service;

import com.example.inventory_service.Entity.Inventory;
import com.example.inventory_service.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    public Inventory getStock(Long productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public String reduceStock(Long productId, int quantity) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getStock() < quantity) {
            return "FAILED";
        }

        inventory.setStock(inventory.getStock() - quantity);
        repository.save(inventory);

        return "SUCCESS";
    }

    public void addStock(Long productId, int quantity) {

        Inventory inventory = repository.findByProductId(productId)
                .orElseGet(() -> {
                    Inventory newInv = new Inventory();
                    newInv.setProductId(productId);
                    newInv.setStock(0);
                    return newInv;
                });

        inventory.setStock(inventory.getStock() + quantity);
        repository.save(inventory);
    }
}