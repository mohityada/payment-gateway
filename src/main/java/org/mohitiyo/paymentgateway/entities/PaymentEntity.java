package org.mohitiyo.paymentgateway.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mohitiyo.paymentgateway.enums.PaymentStatus;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    BigDecimal amount;

    String currency;

    UUID userId;

    @Enumerated(EnumType.STRING)
    PaymentStatus status;

    private LocalDateTime createdAt;
}
