package com.clearflow.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clearflow.settlement.entity.CurrencyReferenceEntity;

public interface CurrencyReferenceRepository
        extends JpaRepository<CurrencyReferenceEntity, String> {

    boolean existsByCurrencyCodeAndSupportedTrue(String currencyCode);
}