package com.clearflow.settlement.exception;

public class DuplicateIngestionRequestException extends RuntimeException {

    public DuplicateIngestionRequestException(
            String requestId,
            String sourceSystem) {

        super(
                "Ingestion request already exists for requestId="
                        + requestId
                        + " and sourceSystem="
                        + sourceSystem);
    }
}