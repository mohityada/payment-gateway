package org.mohitiyo.paymentgateway.services;

import org.mohitiyo.paymentgateway.dto.PaymentInitiatedEvent;
import org.mohitiyo.paymentgateway.entities.PaymentEntity;
import org.mohitiyo.paymentgateway.repositories.PaymentRepository;
import org.mohitiyo.paymentgateway.services.producers.MessageProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final MessageProducer messageProducer;
    PaymentService(PaymentRepository paymentRepository, MessageProducer messageProducer){
        this.paymentRepository = paymentRepository;
        this.messageProducer = messageProducer;
    }

    @Transactional
    public PaymentEntity savePaymentInDB(PaymentEntity paymentEntity){
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);

        PaymentInitiatedEvent paymentInitiatedEvent = new PaymentInitiatedEvent(savedPayment.getId(),
                savedPayment.getAmount(), savedPayment.getUserId(), savedPayment.getCreatedAt());

        messageProducer.publishPaymentMessage(paymentInitiatedEvent);

        return savedPayment;
    }

}
