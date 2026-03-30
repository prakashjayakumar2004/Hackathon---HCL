package com.example.cart_service.Repository;

import com.example.cart_service.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, JpaRepository> {

    Optional<Cart> findByUserId(Long userId);
}
