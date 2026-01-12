package org.mohitiyo.paymentgateway.configurations.rabbitmq;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
@Getter
@Setter
public class RabbitMQProperties {
    private ConfigGroup payment;

    @Getter @Setter
    public static class ConfigGroup {
        private String exchange;
        private String queue;
        private String routingKey;
    }
}
