package com.example.cart_service.Controller;

import com.example.cart_service.DTO.CartRequest;
import com.example.cart_service.Entity.CartItem;
import com.example.cart_service.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("/add")
    public String add(@RequestBody CartRequest req) {
        service.addToCart(req);
        return "Item added";
    }

    @GetMapping("/{userId}")
    public List<CartItem> get(@PathVariable Long userId) {
        return service.getCart(userId);
    }

    @DeleteMapping("/clear/{userId}")
    public String clear(@PathVariable Long userId) {
        service.clearCart(userId);
        return "Cart cleared";
    }
}