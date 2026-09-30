package com.clearflow.settlement.integeration.currency.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CurrencyApiCurrencyResponse(

        String code,

        String name,

        @JsonProperty("decimal_digits")
        Integer decimalDigits,

        String type,

        List<String> countries

) {
}