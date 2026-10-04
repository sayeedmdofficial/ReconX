package com.clearflow.settlement.service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearflow.settlement.entity.CurrencyReferenceEntity;
import com.clearflow.settlement.integeration.currency.dto.CurrencyApiCurrencyResponse;
import com.clearflow.settlement.mapper.CurrencyReferenceMapper;
import com.clearflow.settlement.repository.CurrencyReferenceRepository;

@Service
public class CurrencyReferencePersistenceService {

    private final CurrencyReferenceRepository repository;
    private final CurrencyReferenceMapper mapper;

    public CurrencyReferencePersistenceService(
            CurrencyReferenceRepository repository,
            CurrencyReferenceMapper mapper) {

        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public int persistCurrencies(
            Map<String, CurrencyApiCurrencyResponse> currencies,
            OffsetDateTime syncedAt) {

        List<CurrencyReferenceEntity> existingCurrencies =
                repository.findAllById(currencies.keySet());

        Map<String, CurrencyReferenceEntity> existingByCode =
                new HashMap<>();

        for (CurrencyReferenceEntity entity : existingCurrencies) {
            existingByCode.put(entity.getCurrencyCode(), entity);
        }

        List<CurrencyReferenceEntity> entitiesToSave =
                currencies.values()
                        .stream()
                        .map(currency -> {

                            CurrencyReferenceEntity existing =
                                    existingByCode.get(currency.code());

                            if (existing != null) {

                                mapper.updateExistingEntity(
                                        existing,
                                        currency,
                                        syncedAt);

                                return existing;
                            }

                            return mapper.toNewEntity(
                                    currency,
                                    syncedAt);
                        })
                        .toList();

        repository.saveAll(entitiesToSave);

        return entitiesToSave.size();
    }
}