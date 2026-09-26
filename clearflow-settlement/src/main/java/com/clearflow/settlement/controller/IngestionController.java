package com.clearflow.settlement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearflow.settlement.dto.IngestionRequest;
import com.clearflow.settlement.dto.IngestionResponse;
import com.clearflow.settlement.service.IngestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ingestion")
public class IngestionController {

    private final IngestionService ingestionService;

    @PostMapping
    public ResponseEntity<IngestionResponse> ingestData(@Valid @RequestBody IngestionRequest ingestionRequest) {

        IngestionResponse ingestionResponse = ingestionService.saveIngestionRequest(ingestionRequest);

        return ResponseEntity.ok(ingestionResponse);

    }

}
