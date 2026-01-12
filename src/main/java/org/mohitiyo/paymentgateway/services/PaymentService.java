package org.mohitiyo.paymentgateway.services;

import org.mohitiyo.paymentgateway.dto.PaymentInitiatedEvent;
import org.mohitiyo.paymentgateway.entities.PaymentEntity;
import org.mohitiyo.paymentgateway.repositories.PaymentRepository;
import org.mohitiyo.paymentgateway.services.listeners.PaymentEventListener;
import org.mohitiyo.paymentgateway.services.producers.MessageProducer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventListener paymentEventListener;
    PaymentService(PaymentRepository paymentRepository, PaymentEventListener paymentEventListener){
        this.paymentRepository = paymentRepository;
        this.paymentEventListener = paymentEventListener;
    }

    @Transactional
    public PaymentEntity savePaymentInDB(PaymentEntity paymentEntity){

        // save payment into DB
        PaymentEntity savedPayment = paymentRepository.save(paymentEntity);

        // payload for rabbitmq
        PaymentInitiatedEvent paymentInitiatedEvent = new PaymentInitiatedEvent(savedPayment.getId(),
                savedPayment.getAmount(), savedPayment.getUserId(), savedPayment.getCreatedAt());

        // event listener will send message to rabbitmq after successfully committing into DB
        paymentEventListener.handlePaymentInitiated(paymentInitiatedEvent);

        return savedPayment;
    }

}
