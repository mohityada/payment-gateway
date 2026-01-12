package org.mohitiyo.paymentgateway.services.listeners;

import org.mohitiyo.paymentgateway.dto.PaymentInitiatedEvent;
import org.mohitiyo.paymentgateway.services.producers.MessageProducer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class PaymentEventListener {

    private final MessageProducer messageProducer;

    public PaymentEventListener(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    /**
     * This method runs ONLY after the transaction in PaymentService
     * has successfully committed to the database.
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentInitiated(PaymentInitiatedEvent event) {
        messageProducer.publishPaymentMessage(event);
    }
}