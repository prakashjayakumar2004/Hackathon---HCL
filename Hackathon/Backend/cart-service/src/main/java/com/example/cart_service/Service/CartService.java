package com.example.cart_service.Service;

import com.example.cart_service.DTO.CartRequest;
import com.example.cart_service.Entity.Cart;
import com.example.cart_service.Entity.CartItem;
import com.example.cart_service.Repository.CartItemRepository;
import com.example.cart_service.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public void addToCart(CartRequest request) {

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(request.getUserId());
                    return cartRepository.save(newCart);
                });

        CartItem item = new CartItem();
        item.setCartId(cart.getId());
        item.setProductId(request.getProductId());
        item.setQuantity(request.getQuantity());

        cartItemRepository.save(item);
    }

    public List<CartItem> getCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cartItemRepository.findByCartId(cart.getId());
    }

    public void clearCart(Long userId) {

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cartItemRepository.deleteByCartId(cart.getId());
    }
}