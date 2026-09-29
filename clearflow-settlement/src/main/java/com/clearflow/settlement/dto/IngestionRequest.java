package com.clearflow.settlement.dto;

import com.clearflow.settlement.enums.SourceChannel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record IngestionRequest(

                @NotBlank(message = "requestId is required") @Size(max = 100, message = "requestId must not exceed 100 characters") String requestId,

                @NotBlank(message = "sourceSystem is required") @Size(max = 50, message = "sourceSystem must not exceed 50 characters") String sourceSystem,

                @NotNull(message = "sourceChannel is required") SourceChannel sourceChannel,

                @NotBlank(message = "correlationId is required") @Size(max = 100, message = "correlationId must not exceed 100 characters") String correlationId


) {
}