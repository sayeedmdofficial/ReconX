package com.clearflow.settlement.integeration.currency.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyApiResponse(

        Map<String, CurrencyApiCurrencyResponse> data

) {
}