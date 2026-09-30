package com.clearflow.settlement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearflow.settlement.dto.CurrencyReferenceRefreshResponse;
import com.clearflow.settlement.service.CurrencyReferenceSyncService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("api/v1/internal/reference-data/currencies")
@RequiredArgsConstructor 
public class CurrencyReferenceAdminController {
    private final CurrencyReferenceSyncService currencyReferenceSyncService;

    @PostMapping("/refresh")
    public ResponseEntity<CurrencyReferenceRefreshResponse> refreshCurrencies() {
        return ResponseEntity.ok(currencyReferenceSyncService.refreshCurrencies());
    }
    
}
