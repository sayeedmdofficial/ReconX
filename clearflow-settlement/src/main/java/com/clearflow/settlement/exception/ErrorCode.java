package com.clearflow.settlement.exception;

public enum ErrorCode {

    VALIDATION_FAILED(
            "CF-VAL-001",
            "Request validation failed"),

    MALFORMED_JSON(
            "CF-VAL-002",
            "Malformed JSON request"),

    INVALID_PARAMETER(
            "CF-VAL-003",
            "Invalid request parameter"),

    DUPLICATE_REQUEST(
            "CF-ING-001",
            "Duplicate ingestion request"),

    RESOURCE_NOT_FOUND(
            "CF-GEN-404",
            "Requested resource was not found"),

    BUSINESS_RULE_VIOLATION(
            "CF-BUS-001",
            "Business rule violation"),

    DATA_CONFLICT(
            "CF-DATA-001",
            "Data conflict occurred"),

    INTERNAL_SERVER_ERROR(
            "CF-SYS-001",
            "An unexpected internal error occurred");

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}