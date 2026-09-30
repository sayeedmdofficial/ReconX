package com.clearflow.settlement.integeration.currency;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.clearflow.settlement.integeration.currency.dto.CurrencyApiCurrencyResponse;
import com.clearflow.settlement.integeration.currency.dto.CurrencyApiResponse;

@Component
public class CurrencyApiClient {

    private final RestClient currencyApiRestClient;

    public CurrencyApiClient(
            RestClient currencyApiRestClient) {
        this.currencyApiRestClient = currencyApiRestClient;
    }

    public Map<String, CurrencyApiCurrencyResponse> fetchFiatCurrencies() {

        CurrencyApiResponse response = currencyApiRestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/currencies")
                        .queryParam("type", "fiat")
                        .build())
                .retrieve()
                .body(CurrencyApiResponse.class);

        if (response == null || response.data() == null) {
            throw new IllegalStateException(
                    "Currency API returned an empty response");
        }

        return response.data();
    }
}