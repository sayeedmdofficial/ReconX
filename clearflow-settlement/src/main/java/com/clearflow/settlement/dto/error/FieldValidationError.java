package com.clearflow.settlement.dto.error;

public record FieldValidationError(
        String field,
        String message) {
}