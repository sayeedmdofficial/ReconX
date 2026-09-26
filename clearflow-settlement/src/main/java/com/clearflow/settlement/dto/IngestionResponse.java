package com.clearflow.settlement.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import com.clearflow.settlement.enums.IngestionStatus;
import lombok.Builder;

@Builder
public record IngestionResponse(
        UUID ingestionId,
        String requestId,
        String sourceSystem,
        String correlationId,
        String traceId,
        IngestionStatus status,
        OffsetDateTime receivedAt) {
}
