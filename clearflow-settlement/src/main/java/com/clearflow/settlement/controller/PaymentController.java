package com.clearflow.settlement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearflow.settlement.dto.PaymentInstructionRequest;
import com.clearflow.settlement.dto.PaymentInstructionResponse;
import com.clearflow.settlement.service.PaymentService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping 
    public ResponseEntity<PaymentInstructionResponse> savePaymentDetails(
            @Valid @RequestBody PaymentInstructionRequest paymentInstructionRequest) {
        PaymentInstructionResponse response = paymentService.savePaymentDetails(paymentInstructionRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
