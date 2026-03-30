package com.example.inventory_service.Controller;

import com.example.inventory_service.Entity.Inventory;
import com.example.inventory_service.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @GetMapping("/{productId}")
    public Inventory getStock(@PathVariable Long productId) {
        return service.getStock(productId);
    }

    @PutMapping("/reduce")
    public String reduce(@RequestBody Inventory inventory) {
        return service.reduceStock(
                inventory.getProductId(),
                inventory.getStock()
        );
    }

    @PutMapping("/add")
    public void add(@RequestBody Inventory inventory) {
        service.addStock(
                inventory.getProductId(),
                inventory.getStock()
        );
    }
}