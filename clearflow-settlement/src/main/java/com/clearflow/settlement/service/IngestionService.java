package com.clearflow.settlement.service;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clearflow.settlement.dto.IngestionRequest;
import com.clearflow.settlement.dto.IngestionResponse;
import com.clearflow.settlement.entity.IngestionRequestEntity;
import com.clearflow.settlement.exception.DuplicateIngestionRequestException;
import com.clearflow.settlement.observability.TraceContextProvider;
import com.clearflow.settlement.repository.IngestionRepository;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngestionService {

    private final IngestionRepository ingestionRepository;
    private final TraceContextProvider traceContextProvider;

    @Transactional
    public IngestionResponse saveIngestionRequest(IngestionRequest ingestionRequest) {

        // logic to check if same req id against same source system exists
        log.info(
                "Processing ingestion request requestId={} sourceSystem={}",
                ingestionRequest.requestId(),
                ingestionRequest.sourceSystem());

        // Check For Duplicate Idempotency Request
        ingestionRepository.findByRequestIdAndSourceSystem(
                ingestionRequest.requestId(),
                ingestionRequest.sourceSystem()).ifPresent(existingRequest -> {
                    log.warn(
                            "Duplicate ingestion request detected requestId={} sourceSystem={}",
                            ingestionRequest.requestId(),
                            ingestionRequest.sourceSystem());
                    throw new DuplicateIngestionRequestException(
                            ingestionRequest.requestId(),
                            ingestionRequest.sourceSystem());
                });

        // set timestamp and Trace Id
        OffsetDateTime now = OffsetDateTime.now();

        String traceId = traceContextProvider.getCurrentTraceId();

        // create entity
        IngestionRequestEntity ingestionRequestEntity = IngestionRequestEntity.builder()
                .ingestionId(UUID.randomUUID())
                .requestId(ingestionRequest.requestId())
                .sourceSystem(ingestionRequest.sourceSystem())
                .sourceChannel(ingestionRequest.sourceChannel())
                .correlationId(ingestionRequest.correlationId())
                .traceId(traceId)
                .receivedAt(now)
                .build();

        IngestionRequestEntity savedEntity = ingestionRepository.save(ingestionRequestEntity);

        log.info(
                "Ingestion request persisted ingestionId={} requestId={}",
                savedEntity.getIngestionId(),
                savedEntity.getRequestId());

        IngestionResponse ingestionResponse = IngestionResponse.builder()
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