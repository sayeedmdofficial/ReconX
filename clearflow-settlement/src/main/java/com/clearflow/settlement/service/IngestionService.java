package com.clearflow.settlement.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearflow.settlement.dto.IngestionRequest;
import com.clearflow.settlement.dto.IngestionResponse;
import com.clearflow.settlement.entity.IngestionRequestEntity;
import com.clearflow.settlement.exception.DuplicateIngestionRequestException;
import com.clearflow.settlement.repository.IngestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngestionService {

    private final IngestionRepository ingestionRepository;

    @Transactional
    public IngestionResponse saveIngestionRequest(IngestionRequest ingestionRequest) {
        
        //logic to check if same req id against same source system exists
        
        ingestionRepository.findByRequestIdAndSourceSystem(
            ingestionRequest.requestId(),
            ingestionRequest.sourceSystem()
        ).ifPresent(existingRequest -> {
            throw new DuplicateIngestionRequestException(
                ingestionRequest.requestId(),
                ingestionRequest.sourceSystem()
            );
        });


        OffsetDateTime now = OffsetDateTime.now();

        IngestionRequestEntity ingestionRequestEntity = IngestionRequestEntity.builder()
                .ingestionId(UUID.randomUUID())
                .requestId(ingestionRequest.requestId())
                .sourceSystem(ingestionRequest.sourceSystem())
                .sourceChannel(ingestionRequest.sourceChannel())
                .correlationId(ingestionRequest.correlationId())
                .traceId(ingestionRequest.traceId())
                .receivedAt(now)
                .build();

        IngestionRequestEntity savedEntity = ingestionRepository.save(ingestionRequestEntity);

        IngestionResponse ingestionResponse =
        IngestionResponse.builder()
                .ingestionId(savedEntity.getIngestionId())
                .requestId(savedEntity.getRequestId())
                .sourceSystem(savedEntity.getSourceSystem())
                .correlationId(savedEntity.getCorrelationId())
                .traceId(savedEntity.getTraceId())
                .status(savedEntity.getStatus())
                .receivedAt(savedEntity.getReceivedAt())
                .build();

        return ingestionResponse;
    }
}