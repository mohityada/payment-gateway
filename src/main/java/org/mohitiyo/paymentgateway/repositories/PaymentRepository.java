package org.mohitiyo.paymentgateway.repositories;

import org.mohitiyo.paymentgateway.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID> {

}
