package com.uns.ftn.sciencejournal.dto.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import java.time.LocalDateTime;
import java.util.Objects;

public class SubscriptionPurchaseDTO {

    private Long id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private PurchaseType type;

    private Boolean successful;

    private Integer paymentOption;

    private Long subscription;

    private Double amount;

    public SubscriptionPurchaseDTO() {
    }

    public SubscriptionPurchaseDTO(Double amount, Long id, LocalDateTime timeOfPurchase, String user,
                                   PurchaseType type, Boolean successful, Integer paymentOption, Long subscription) {
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.type = type;
        this.successful = successful;
        this.paymentOption = paymentOption;
        this.subscription = subscription;
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
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

    public Long getSubscription() {
        return subscription;
    }

    public void setSubscription(Long subscription) {
        this.subscription = subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPurchaseDTO that = (SubscriptionPurchaseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeOfPurchase, that.timeOfPurchase) &&
                Objects.equals(user, that.user) &&
                type == that.type &&
                Objects.equals(successful, that.successful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(subscription, that.subscription) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOfPurchase, user, type, successful, paymentOption, subscription, amount);
    }

    @Override
    public String toString() {
        return "SubscriptionPurchaseDTO{" +
                "id=" + id +
                ", timeOfPurchase=" + timeOfPurchase +
                ", user='" + user + '\'' +
                ", type=" + type +
                ", successful=" + successful +
                ", paymentOption=" + paymentOption +
                ", subscription=" + subscription +
                ", amount=" + amount +
                '}';
    }
}
