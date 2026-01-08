package org.mohitiyo.paymentgateway.controllers;

import jakarta.validation.Valid;
import org.mohitiyo.paymentgateway.dto.PaymentRequest;
import org.mohitiyo.paymentgateway.entities.PaymentEntity;
import org.mohitiyo.paymentgateway.enums.PaymentStatus;
import org.mohitiyo.paymentgateway.repositories.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping
    public ResponseEntity<PaymentEntity> initiatePayment(@Valid @RequestBody PaymentRequest request){
        PaymentEntity payment = PaymentEntity.builder()
                .userId(request.userId())
                .amount(request.amount())
                .currency(request.currency())
                .status(PaymentStatus.INITIATED) // Always starts as INITIATED
                .createdAt(LocalDateTime.now())
                .build();

        PaymentEntity savedPayment = paymentRepository.save(payment);
        return ResponseEntity.accepted().body(savedPayment);
    }

}
