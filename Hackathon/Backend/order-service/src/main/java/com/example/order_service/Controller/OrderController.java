package com.example.order_service.Controller;

import com.example.order_service.DTO.OrderRequest;
import com.example.order_service.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/place")
    public String placeOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request);
    }
}