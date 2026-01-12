package org.mohitiyo.paymentgateway.services.producers;

import lombok.extern.slf4j.Slf4j;
import org.mohitiyo.paymentgateway.configurations.rabbitmq.RabbitMQProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageProducer {

    protected final RabbitTemplate rabbitTemplate;
    protected final RabbitMQProperties properties;

    public MessageProducer(RabbitTemplate rabbitTemplate, RabbitMQProperties properties) {
        this.rabbitTemplate = rabbitTemplate;
        this.properties = properties;
    }


    public void publish(String exchange, String routingKey, Object message) {
        log.info("Publishing to exchange: {}, routingKey: {}", exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void publishPaymentMessage(Object message) {
        // Fetch values dynamically from your properties
        String exchange = properties.getPayment().getExchange();
        String routingKey = properties.getPayment().getRoutingKey();
        publish(exchange, routingKey, message);
    }
}
