package com.clearflow.settlement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clearflow.integrations.currency-api")
public record CurrencyApiProperties(
        String baseUrl,
        String apiKey) {
}