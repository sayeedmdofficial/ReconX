package com.clearflow.settlement.dto.error;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ApiErrorResponse(

                OffsetDateTime timestamp,

                int status,

                String error,

                String errorCode,

                String message,

                String path,

                String traceId,

                List<FieldValidationError> fieldErrors

) {
}