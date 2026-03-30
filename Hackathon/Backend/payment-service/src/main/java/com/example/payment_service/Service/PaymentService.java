package com.example.payment_service.Service;


import com.example.payment_service.DTO.PaymentRequest;
import com.example.payment_service.DTO.PaymentResponse;
import com.example.payment_service.Entity.Payment;
import com.example.payment_service.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public PaymentResponse processPayment(PaymentRequest request) {

        String status;

        if (request.getUpiPin() != null && request.getUpiPin().length() == 4) {
            status = "SUCCESS";
        } else {
            status = "FAILED";
        }

        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setUpiId(request.getUpiId());
        payment.setAmount(request.getAmount());
        payment.setStatus(status);

        repository.save(payment);

        return new PaymentResponse(status);
    }

    public Payment getByOrderId(Long orderId) {
        return repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
