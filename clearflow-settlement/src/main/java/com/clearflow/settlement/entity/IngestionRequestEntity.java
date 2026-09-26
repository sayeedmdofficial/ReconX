package com.clearflow.settlement.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.clearflow.settlement.enums.IngestionStatus;
import com.clearflow.settlement.enums.SourceChannel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "CF_INGESTION_REQUEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngestionRequestEntity {

    @Id
    @Column(
            name = "INGESTION_ID",
            nullable = false,
            updatable = false,
            columnDefinition = "RAW(16)"
    )
    private UUID ingestionId;

    @Column(
            name = "PAYMENT_ID",
            columnDefinition = "RAW(16)"
    )
    private UUID paymentId;

    @Column(
            name = "REQUEST_ID",
            nullable = false,
            length = 100,
            updatable = false
    )
    private String requestId;

    @Column(
            name = "SOURCE_SYSTEM",
            nullable = false,
            length = 50,
            updatable = false
    )
    private String sourceSystem;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "SOURCE_CHANNEL",
            nullable = false,
            length = 30,
            updatable = false
    )
    private SourceChannel sourceChannel;

    @Column(
            name = "CORRELATION_ID",
            nullable = false,
            length = 100,
            updatable = false
    )
    private String correlationId;

    @Column(
            name = "TRACE_ID",
            length = 64,
            updatable = false
    )
    private String traceId;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "STATUS",
            nullable = false,
            length = 30
    )
    private IngestionStatus status;

    @Column(
            name = "RECEIVED_AT",
            nullable = false,
            updatable = false
    )
    private OffsetDateTime receivedAt;

    @Column(name = "PROCESSING_STARTED_AT")
    private OffsetDateTime processingStartedAt;

    @Column(name = "PROCESSING_COMPLETED_AT")
    private OffsetDateTime processingCompletedAt;

    @Column(
            name = "ERROR_CODE",
            length = 100
    )
    private String errorCode;

    @Column(
            name = "ERROR_MESSAGE",
            length = 1000
    )
    private String errorMessage;

    @Column(
            name = "UPDATED_AT",
            nullable = false
    )
    private OffsetDateTime updatedAt;

    @Builder
    private IngestionRequestEntity(
            UUID ingestionId,
            String requestId,
            String sourceSystem,
            SourceChannel sourceChannel,
            String correlationId,
            String traceId,
            OffsetDateTime receivedAt) {

        this.ingestionId = ingestionId;
        this.requestId = requestId;
        this.sourceSystem = sourceSystem;
        this.sourceChannel = sourceChannel;
        this.correlationId = correlationId;
        this.traceId = traceId;

        this.status = IngestionStatus.RECEIVED;
        this.receivedAt = receivedAt;
        this.updatedAt = receivedAt;
    }

    public void assignPayment(
            UUID paymentId,
            OffsetDateTime updatedAt) {

        this.paymentId = paymentId;
        this.updatedAt = updatedAt;
    }

    public void markProcessing(OffsetDateTime startedAt) {

        this.status = IngestionStatus.PROCESSING;
        this.processingStartedAt = startedAt;
        this.updatedAt = startedAt;
    }

    public void markCompleted(OffsetDateTime completedAt) {

        this.status = IngestionStatus.COMPLETED;
        this.processingCompletedAt = completedAt;
        this.updatedAt = completedAt;
    }

    public void markFailed(
            String errorCode,
            String errorMessage,
            OffsetDateTime failedAt) {

        this.status = IngestionStatus.FAILED;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.updatedAt = failedAt;
    }
}