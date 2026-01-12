package org.mohitiyo.paymentgateway.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentInitiatedEvent(
        UUID paymentId,
        BigDecimal amount,
        UUID userId,
        LocalDateTime occurredAt
) {}