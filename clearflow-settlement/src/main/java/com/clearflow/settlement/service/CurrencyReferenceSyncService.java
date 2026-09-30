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

    public CurrencyReferenceSyncService(
            CurrencyApiClient currencyApiClient) {
        this.currencyApiClient = currencyApiClient;
    }

    public CurrencyReferenceRefreshResponse refreshCurrencies() {

        Map<String, CurrencyApiCurrencyResponse> currencies =
                currencyApiClient.fetchFiatCurrencies();

        int fetchedCount = currencies.size();

        return new CurrencyReferenceRefreshResponse(
                "SUCCESS",
                fetchedCount,
                0,
                OffsetDateTime.now());
    }
}