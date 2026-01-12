package org.mohitiyo.paymentgateway.entities;

import jakarta.persistence.*;
import lombok.*;
import org.mohitiyo.paymentgateway.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
