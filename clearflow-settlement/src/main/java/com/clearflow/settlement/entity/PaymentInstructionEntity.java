package com.clearflow.settlement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.clearflow.settlement.enums.PaymentStatus;
import com.clearflow.settlement.enums.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "CF_PAYMENT_INSTRUCTION")
@Data
@Builder 
public class PaymentInstructionEntity {

    @Id
    @Column(name = "PAYMENT_ID", nullable = false)
    private UUID paymentId;

    @Column(name = "INGESTION_ID")
    private UUID ingestionId;

    @Column(
            name = "PAYMENT_REFERENCE",
            nullable = false,
            length = 64,
            unique = true
    )
    private String paymentReference;

    @Column(
            name = "SOURCE_SYSTEM",
            nullable = false,
            length = 50
    )
    private String sourceSystem;

    @Column(
            name = "EXTERNAL_REFERENCE",
            nullable = false,
            length = 100
    )
    private String externalReference;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "PAYMENT_TYPE",
            nullable = false,
            length = 30
    )
    private PaymentType paymentType;

    @Column(
            name = "DEBTOR_PARTICIPANT_ID",
            nullable = false,
            length = 35
    )
    private String debtorParticipantId;

    @Column(
            name = "CREDITOR_PARTICIPANT_ID",
            nullable = false,
            length = 35
    )
    private String creditorParticipantId;

    @Column(
            name = "DEBTOR_ACCOUNT_REF",
            nullable = false,
            length = 64
    )
    private String debtorAccountRef;

    @Column(
            name = "CREDITOR_ACCOUNT_REF",
            nullable = false,
            length = 64
    )
    private String creditorAccountRef;

    @Column(
            name = "AMOUNT",
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal amount;

    @Column(
            name = "CURRENCY",
            nullable = false,
            length = 3
    )
    private String currency;

    @Column(
            name = "REQUESTED_SETTLEMENT_DATE",
            nullable = false
    )
    private LocalDate requestedSettlementDate;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "STATUS",
            nullable = false,
            length = 30
    )
    private PaymentStatus status;

    @Version
    @Column(
            name = "VERSION_NO",
            nullable = false
    )
    private Long versionNo;

    @Column(
            name = "CREATED_AT",
            nullable = false,
            updatable = false
    )
    private OffsetDateTime createdAt;

    @Column(
            name = "UPDATED_AT",
            nullable = false
    )
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {

        OffsetDateTime now = OffsetDateTime.now();

        if (paymentId == null) {
            paymentId = UUID.randomUUID();
        }

        if (status == null) {
            status = PaymentStatus.RECEIVED;
        }

        if (createdAt == null) {
            createdAt = now;
        }

        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public UUID getIngestionId() {
        return ingestionId;
    }

    public void setIngestionId(UUID ingestionId) {
        this.ingestionId = ingestionId;
    }

    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getDebtorParticipantId() {
        return debtorParticipantId;
    }

    public void setDebtorParticipantId(String debtorParticipantId) {
        this.debtorParticipantId = debtorParticipantId;
    }

    public String getCreditorParticipantId() {
        return creditorParticipantId;
    }

    public void setCreditorParticipantId(String creditorParticipantId) {
        this.creditorParticipantId = creditorParticipantId;
    }

    public String getDebtorAccountRef() {
        return debtorAccountRef;
    }

    public void setDebtorAccountRef(String debtorAccountRef) {
        this.debtorAccountRef = debtorAccountRef;
    }

    public String getCreditorAccountRef() {
        return creditorAccountRef;
    }

    public void setCreditorAccountRef(String creditorAccountRef) {
        this.creditorAccountRef = creditorAccountRef;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getRequestedSettlementDate() {
        return requestedSettlementDate;
    }

    public void setRequestedSettlementDate(LocalDate requestedSettlementDate) {
        this.requestedSettlementDate = requestedSettlementDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getVersionNo() {
        return versionNo;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}