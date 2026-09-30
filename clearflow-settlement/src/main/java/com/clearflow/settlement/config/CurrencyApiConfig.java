package com.clearflow.settlement.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(CurrencyApiProperties.class)
public class CurrencyApiConfig {

    @Bean
    RestClient currencyApiRestClient(
            CurrencyApiProperties properties) {

        return RestClient.builder()
                .baseUrl(properties.baseUrl())
                .defaultHeader("apikey", properties.apiKey())
                .build();
    }
}