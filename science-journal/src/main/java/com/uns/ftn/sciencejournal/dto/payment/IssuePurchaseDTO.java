package com.uns.ftn.sciencejournal.dto.payment;

import com.uns.ftn.sciencejournal.model.common.Issue;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import java.time.LocalDateTime;
import java.util.Objects;

public class IssuePurchaseDTO {

    private Long id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private PurchaseType type;

    private Boolean successful;

    private Integer paymentOption;

    private Long issuePK;

    private Double amount;

    public IssuePurchaseDTO() {
    }

    public IssuePurchaseDTO(Long id, LocalDateTime timeOfPurchase, String user, PurchaseType type,
                            Boolean successful, Integer paymentOption, Long issuePK, Double amount) {
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.type = type;
        this.successful = successful;
        this.paymentOption = paymentOption;
        this.issuePK = issuePK;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public PurchaseType getType() {
        return type;
    }

    public void setType(PurchaseType type) {
        this.type = type;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public Integer getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(Integer paymentOption) {
        this.paymentOption = paymentOption;
    }

    public Long getIssuePK() {
        return issuePK;
    }

    public void setIssuePK(Long issuePK) {
        this.issuePK = issuePK;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "IssuePurchaseDTO{" +
                "id=" + id +
                ", timeOfPurchase=" + timeOfPurchase +
                ", user='" + user + '\'' +
                ", type=" + type +
                ", successful=" + successful +
                ", paymentOption=" + paymentOption +
                ", issuePK=" + issuePK +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuePurchaseDTO that = (IssuePurchaseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeOfPurchase, that.timeOfPurchase) &&
                Objects.equals(user, that.user) &&
                type == that.type &&
                Objects.equals(successful, that.successful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(issuePK, that.issuePK) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOfPurchase, user, type, successful, paymentOption, issuePK, amount);
    }
}