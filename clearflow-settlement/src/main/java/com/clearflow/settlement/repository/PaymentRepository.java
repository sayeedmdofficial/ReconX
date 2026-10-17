package com.clearflow.settlement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearflow.settlement.entity.PaymentInstructionEntity;

public interface PaymentRepository extends JpaRepository<PaymentInstructionEntity, UUID> {
    Optional<PaymentInstructionEntity> findByPaymentReference(
            String paymentReference);

    boolean existsBySourceSystemAndExternalReference(
            String sourceSystem,
            String externalReference);

    Optional<PaymentInstructionEntity> findByIngestionId(
            UUID ingestionId);

    
}
