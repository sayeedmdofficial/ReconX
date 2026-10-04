package com.clearflow.settlement.service;

import java.time.OffsetDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.clearflow.settlement.dto.CurrencyReferenceRefreshResponse;
import com.clearflow.settlement.integeration.currency.CurrencyApiClient;
import com.clearflow.settlement.integeration.currency.dto.CurrencyApiCurrencyResponse;

@Service
public class CurrencyReferenceSyncService {

    private final CurrencyApiClient currencyApiClient;
    private final CurrencyReferencePersistenceService persistenceService;

    public CurrencyReferenceSyncService(
            CurrencyApiClient currencyApiClient,
            CurrencyReferencePersistenceService persistenceService) {

        this.currencyApiClient = currencyApiClient;
        this.persistenceService = persistenceService;
    }

    public CurrencyReferenceRefreshResponse refreshCurrencies() {

        Map<String, CurrencyApiCurrencyResponse> currencies =
                currencyApiClient.fetchFiatCurrencies();

        OffsetDateTime refreshedAt = OffsetDateTime.now();

        int fetchedCount = currencies.size();

        int persistedCount =
                persistenceService.persistCurrencies(
                        currencies,
                        refreshedAt);

        return new CurrencyReferenceRefreshResponse(
                "SUCCESS",
                fetchedCount,
                persistedCount,
                refreshedAt);
    }
}