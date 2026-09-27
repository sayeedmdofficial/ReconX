package com.clearflow.settlement.exception;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.clearflow.settlement.dto.error.ApiErrorResponse;
import com.clearflow.settlement.dto.error.FieldValidationError;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*
     * -------------------------------------------------------
     * Bean validation errors
     * -------------------------------------------------------
     *
     * Handles:
     *
     * @NotNull
     * 
     * @NotBlank
     * 
     * @Size
     * 
     * @Pattern
     * 
     * @Positive
     * etc.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request) {

        List<FieldValidationError> fieldErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldValidationError(
                        error.getField(),
                        error.getDefaultMessage()))
                .toList();

        log.warn(
                "Request validation failed. path={}, violations={}",
                request.getRequestURI(),
                fieldErrors.size());

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_FAILED,
                ErrorCode.VALIDATION_FAILED.getDefaultMessage(),
                request,
                fieldErrors);
    }

    /*
     * -------------------------------------------------------
     * Constraint violations
     * -------------------------------------------------------
     *
     * Usually useful for request parameters/path variables.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolation(
            ConstraintViolationException exception,
            HttpServletRequest request) {

        List<FieldValidationError> fieldErrors = exception.getConstraintViolations()
                .stream()
                .map(violation -> new FieldValidationError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .toList();

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.VALIDATION_FAILED,
                ErrorCode.VALIDATION_FAILED.getDefaultMessage(),
                request,
                fieldErrors);
    }

    /*
     * -------------------------------------------------------
     * Invalid / broken JSON
     * -------------------------------------------------------
     *
     * Example:
     *
     * {
     * "sourceChannel": ABC
     * }
     *
     * or invalid enum values.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedJson(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {

        log.warn(
                "Malformed request payload. path={}",
                request.getRequestURI());

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.MALFORMED_JSON,
                ErrorCode.MALFORMED_JSON.getDefaultMessage(),
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * Invalid path/query parameter types
     * -------------------------------------------------------
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request) {

        String message = "Invalid value supplied for parameter: "
                + exception.getName();

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ErrorCode.INVALID_PARAMETER,
                message,
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * Duplicate / idempotency error
     * -------------------------------------------------------
     */
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicateRequest(
            DuplicateRequestException exception,
            HttpServletRequest request) {

        log.warn(
                "Duplicate ingestion request. path={}, message={}",
                request.getRequestURI(),
                exception.getMessage());

        return buildResponse(
                HttpStatus.CONFLICT,
                ErrorCode.DUPLICATE_REQUEST,
                exception.getMessage(),
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * Resource not found
     * -------------------------------------------------------
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request) {

        return buildResponse(
                HttpStatus.NOT_FOUND,
                ErrorCode.RESOURCE_NOT_FOUND,
                exception.getMessage(),
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * Business validation
     * -------------------------------------------------------
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessRuleViolation(
            BusinessRuleException exception,
            HttpServletRequest request) {

        log.warn(
                "Business rule violation. path={}, message={}",
                request.getRequestURI(),
                exception.getMessage());

        return buildResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                ErrorCode.BUSINESS_RULE_VIOLATION,
                exception.getMessage(),
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * Database integrity constraint
     * -------------------------------------------------------
     *
     * Example:
     *
     * UNIQUE constraint
     * FK violation
     * duplicate key
     *
     * Never send Oracle/Hibernate details to API consumers.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolation(
            DataIntegrityViolationException exception,
            HttpServletRequest request) {

        log.error(
                "Database integrity violation. path={}",
                request.getRequestURI(),
                exception);

        return buildResponse(
                HttpStatus.CONFLICT,
                ErrorCode.DATA_CONFLICT,
                ErrorCode.DATA_CONFLICT.getDefaultMessage(),
                request,
                List.of());
    }

    /*
     * -------------------------------------------------------
     * FINAL SAFETY NET
     * -------------------------------------------------------
     *
     * Nothing should escape this handler as raw stack trace /
     * Spring Boot default response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleUnexpectedException(
            Exception exception,
            HttpServletRequest request) {

        log.error(
                "Unexpected exception while processing request. path={}",
                request.getRequestURI(),
                exception);

        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR.getDefaultMessage(),
                request,
                List.of());
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            ErrorCode errorCode,
            String message,
            HttpServletRequest request,
            List<FieldValidationError> fieldErrors) {

        String traceId = MDC.get("traceId");

        ApiErrorResponse response = new ApiErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                errorCode.getCode(),
                message,
                request.getRequestURI(),
                traceId,
                fieldErrors);

        return ResponseEntity
                .status(status)
                .body(response);
    }
}