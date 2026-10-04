package com.clearflow.settlement.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "CF_CURRENCY_REFERENCE")
public class CurrencyReferenceEntity {

    @Id
    @Column(name = "CURRENCY_CODE", length = 3, nullable = false)
    private String currencyCode;

    @Column(name = "CURRENCY_NAME", length = 100, nullable = false)
    private String currencyName;

    @Column(name = "SYMBOL", length = 20)
    private String symbol;

    @Column(name = "DECIMAL_DIGITS", nullable = false)
    private Integer decimalDigits;

    @Column(name = "CURRENCY_TYPE", length = 20, nullable = false)
    private String currencyType;

    @Column(name = "SUPPORTED", nullable = false)
    private Boolean supported;

    @Column(name = "PROVIDER", length = 50, nullable = false)
    private String provider;

    @Column(name = "LAST_SYNCED_AT", nullable = false)
    private OffsetDateTime lastSyncedAt;

    @Column(name = "CREATED_AT", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UPDATED_AT", nullable = false)
    private OffsetDateTime updatedAt;

    @Version
    @Column(name = "VERSION_NO", nullable = false)
    private Long versionNo;

    protected CurrencyReferenceEntity() {
    }

    public CurrencyReferenceEntity(
            String currencyCode,
            String currencyName,
            String symbol,
            Integer decimalDigits,
            String currencyType,
            Boolean supported,
            String provider,
            OffsetDateTime lastSyncedAt,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt) {

        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.symbol = symbol;
        this.decimalDigits = decimalDigits;
        this.currencyType = currencyType;
        this.supported = supported;
        this.provider = provider;
        this.lastSyncedAt = lastSyncedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public OffsetDateTime getLastSyncedAt() {
        return lastSyncedAt;
    }

    public void setLastSyncedAt(OffsetDateTime lastSyncedAt) {
        this.lastSyncedAt = lastSyncedAt;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersionNo() {
        return versionNo;
    }
}
