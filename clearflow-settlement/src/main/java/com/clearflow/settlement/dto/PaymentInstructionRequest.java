package com.clearflow.settlement.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.clearflow.settlement.enums.PaymentType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PaymentInstructionRequest(

        @NotNull(message = "ingestionId is required")
        UUID ingestionId,

        @NotBlank(message = "externalReference is required")
        @Size(max = 100, message = "externalReference must not exceed 100 characters")
        String externalReference,

        @NotNull(message = "paymentType is required")
        PaymentType paymentType,

        @NotBlank(message = "debtorParticipantId is required")
        @Size(max = 35, message = "debtorParticipantId must not exceed 35 characters")
        String debtorParticipantId,

        @NotBlank(message = "creditorParticipantId is required")
        @Size(max = 35, message = "creditorParticipantId must not exceed 35 characters")
        String creditorParticipantId,

        @NotBlank(message = "debtorAccountRef is required")
        @Size(max = 64, message = "debtorAccountRef must not exceed 64 characters")
        String debtorAccountRef,

        @NotBlank(message = "creditorAccountRef is required")
        @Size(max = 64, message = "creditorAccountRef must not exceed 64 characters")
        String creditorAccountRef,

        @NotNull(message = "amount is required")
        @DecimalMin(value = "0.0001", message = "amount must be greater than 0")
        @Digits(integer = 15, fraction = 4, message = "amount must have up to 15 integer digits and 4 decimal places")
        BigDecimal amount,

        @NotBlank(message = "currency is required")
        @Pattern(
                regexp = "^[A-Z]{3}$",
                message = "currency must be a 3-letter uppercase ISO currency code"
        )
        String currency,

        @NotNull(message = "requestedSettlementDate is required")
        LocalDate requestedSettlementDate

) {
}