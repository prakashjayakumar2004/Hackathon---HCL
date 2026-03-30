package com.example.order_service.Service;

import com.example.order_service.DTO.*;
import com.example.order_service.Entity.OrderItem;
import com.example.order_service.Entity.Orders;
import com.example.order_service.Repository.OrderItemRepository;
import com.example.order_service.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderItemRepository itemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public String placeOrder(OrderRequest request) {

        Long userId = request.getUserId();

        List<CartItem> cartItems = Arrays.asList(
                restTemplate.getForObject(
                        "http://localhost:8083/cart/" + userId,
                        CartItem[].class
                )
        );

        if (cartItems.isEmpty()) {
            return "Cart is empty";
        }

        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getQuantity() * 100;
        }

        Orders order = new Orders();
        order.setUserId(userId);
        order.setTotalAmount(total);
        order.setStatus("PENDING");

        Orders savedOrder = ordersRepository.save(order);

        PaymentRequest paymentReq = new PaymentRequest();
        paymentReq.setOrderId(savedOrder.getId());
        paymentReq.setUpiId(request.getUpiId());
        paymentReq.setUpiPin(request.getUpiPin());
        paymentReq.setAmount(total);

        PaymentResponse paymentRes = restTemplate.postForObject(
                "http://localhost:8086/payment/pay",
                paymentReq,
                PaymentResponse.class
        );

        if (!paymentRes.getStatus().equals("SUCCESS")) {
            savedOrder.setStatus("FAILED");
            ordersRepository.save(savedOrder);
            return "Payment Failed";
        }

        savedOrder.setStatus("CONFIRMED");
        ordersRepository.save(savedOrder);

        for (CartItem item : cartItems) {

            OrderItem oi = new OrderItem();
            oi.setOrderId(savedOrder.getId());
            oi.setProductId(item.getProductId());
            oi.setQuantity(item.getQuantity());

            itemRepo.save(oi);

            InventoryRequest invReq = new InventoryRequest();
            invReq.setProductId(item.getProductId());
            invReq.setStock(item.getQuantity());

            restTemplate.put(
                    "http://localhost:8085/inventory/reduce",
                    invReq
            );
        }

        restTemplate.delete("http://localhost:8083/cart/clear/" + userId);

        return "Order Placed Successfully";
    }
}