package com.clearflow.settlement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.clearflow.settlement.enums.PaymentStatus;
import com.clearflow.settlement.enums.PaymentType;

public record PaymentInstructionResponse(

        UUID paymentId,

        UUID ingestionId,

        String paymentReference,

        String sourceSystem,

        String externalReference,

        PaymentType paymentType,

        String debtorParticipantId,

        String creditorParticipantId,

        BigDecimal amount,

        String currency,

        LocalDate requestedSettlementDate,

        PaymentStatus status,

        OffsetDateTime createdAt

) {
}