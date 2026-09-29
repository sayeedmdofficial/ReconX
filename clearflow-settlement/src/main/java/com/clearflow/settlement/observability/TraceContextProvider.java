package com.clearflow.settlement.observability;

import org.springframework.stereotype.Component;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TraceContextProvider {

    private final Tracer tracer;

    public String getCurrentTraceId() {

        Span currentSpan = tracer.currentSpan();

        if (currentSpan == null) {
            throw new IllegalStateException(
                    "No active trace context available");
        }

        return currentSpan.context().traceId();
    }

    public String getCurrentSpanId() {

        Span currentSpan = tracer.currentSpan();

        if (currentSpan == null) {
            throw new IllegalStateException(
                    "No active trace context available");
        }

        return currentSpan.context().spanId();
    }
}