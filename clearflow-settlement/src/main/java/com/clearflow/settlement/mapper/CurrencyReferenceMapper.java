package com.clearflow.settlement.mapper;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.clearflow.settlement.entity.CurrencyReferenceEntity;
import com.clearflow.settlement.integeration.currency.dto.CurrencyApiCurrencyResponse;

@Component
public class CurrencyReferenceMapper {

    private static final String PROVIDER = "CURRENCY_API";

    public CurrencyReferenceEntity toNewEntity(
            CurrencyApiCurrencyResponse source,
            OffsetDateTime syncedAt) {

        return new CurrencyReferenceEntity(
                source.code(),
                source.name(),
                source.symbol(),
                source.decimalDigits(),
                source.type(),

                // External provider mein hona != ClearFlow supported hona
                false,

                PROVIDER,
                syncedAt,
                syncedAt,
                syncedAt);
    }

    public void updateExistingEntity(
            CurrencyReferenceEntity entity,
            CurrencyApiCurrencyResponse source,
            OffsetDateTime syncedAt) {

        entity.setCurrencyName(source.name());
        entity.setSymbol(source.symbol());
        entity.setDecimalDigits(source.decimalDigits());
        entity.setCurrencyType(source.type());
        entity.setProvider(PROVIDER);
        entity.setLastSyncedAt(syncedAt);
        entity.setUpdatedAt(syncedAt);

        // IMPORTANT:
        // supported ko intentionally update nahi karenge.
    }
}