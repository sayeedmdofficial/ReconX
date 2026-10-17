package com.clearflow.settlement.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.clearflow.settlement.dto.PaymentInstructionRequest;
import com.clearflow.settlement.dto.PaymentInstructionResponse;
import com.clearflow.settlement.entity.IngestionRequestEntity;
import com.clearflow.settlement.entity.PaymentInstructionEntity;
import com.clearflow.settlement.enums.PaymentStatus;
import com.clearflow.settlement.exception.DuplicatePaymentException;
import com.clearflow.settlement.repository.IngestionRepository;
import com.clearflow.settlement.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

        private final PaymentRepository paymentRepository;
        private final IngestionRepository ingestionRepository;

        @Transactional
        public PaymentInstructionResponse savePaymentDetails(
                        PaymentInstructionRequest paymentInstructionRequest) {

                IngestionRequestEntity ingestion = ingestionRepository
                                .findById(paymentInstructionRequest.ingestionId())
                                .orElseThrow(() -> new RuntimeException("Ingestion request not found"));

                String sourceSystem = ingestion.getSourceSystem();
                String externalReference = paymentInstructionRequest.externalReference();
                boolean paymentExist = paymentRepository.existsBySourceSystemAndExternalReference(sourceSystem,
                                externalReference);

                if (paymentExist) {
                        throw new DuplicatePaymentException(
                                        "Payment already exists for sourceSystem: "
                                                        + sourceSystem
                                                        + " and externalReference: "
                                                        + externalReference);
                }

                PaymentInstructionEntity paymentInstructionEntity = PaymentInstructionEntity.builder()
                                .paymentId(UUID.randomUUID())
                                .ingestionId(paymentInstructionRequest.ingestionId())
                                .paymentReference(generatePaymentReference())
                                .sourceSystem(ingestion.getSourceSystem())
                                .externalReference(
                                                paymentInstructionRequest.externalReference())
                                .paymentType(
                                                paymentInstructionRequest.paymentType())
                                .debtorParticipantId(
                                                paymentInstructionRequest.debtorParticipantId())
                                .creditorParticipantId(
                                                paymentInstructionRequest.creditorParticipantId())
                                .debtorAccountRef(
                                                paymentInstructionRequest.debtorAccountRef())
                                .creditorAccountRef(
                                                paymentInstructionRequest.creditorAccountRef())
                                .amount(
                                                paymentInstructionRequest.amount())
                                .currency(
                                                paymentInstructionRequest.currency())
                                .requestedSettlementDate(
                                                paymentInstructionRequest.requestedSettlementDate())
                                .status(PaymentStatus.RECEIVED)
                                .build();

                PaymentInstructionEntity savedPayment = paymentRepository.save(paymentInstructionEntity);

                return new PaymentInstructionResponse(
                                savedPayment.getPaymentId(),
                                savedPayment.getIngestionId(),
                                savedPayment.getPaymentReference(),
                                savedPayment.getSourceSystem(),
                                savedPayment.getExternalReference(),
                                savedPayment.getPaymentType(),
                                savedPayment.getDebtorParticipantId(),
                                savedPayment.getCreditorParticipantId(),
                                savedPayment.getAmount(),
                                savedPayment.getCurrency(),
                                savedPayment.getRequestedSettlementDate(),
                                savedPayment.getStatus(),
                                savedPayment.getCreatedAt());
        }

        private String generatePaymentReference() {
                return "CF-PAY-" + UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase();
        }
}