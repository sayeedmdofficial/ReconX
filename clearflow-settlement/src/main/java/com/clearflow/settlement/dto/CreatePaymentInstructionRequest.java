package com.clearflow.settlement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;



public record CreatePaymentInstructionRequest(

        String paymentReference,

        String sourceSystem,

        String externalReference,

        String paymentType,

        String debtorParticipantId,

        String creditorParticipantId,

        String debtorAccountRef,

        String creditorAccountRef,

        BigDecimal amount,

        String currency,

        LocalDate requestedSettlementDate
) {
}