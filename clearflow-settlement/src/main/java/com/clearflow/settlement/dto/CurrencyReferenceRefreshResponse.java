package com.clearflow.settlement.dto;


import java.time.OffsetDateTime;

public record CurrencyReferenceRefreshResponse(
        String status,
        int fetchedCount,
        int persistedCount,
        OffsetDateTime refreshedAt
) {
}