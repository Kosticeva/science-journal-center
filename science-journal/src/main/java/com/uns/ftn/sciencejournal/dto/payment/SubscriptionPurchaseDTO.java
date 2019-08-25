package com.uns.ftn.sciencejournal.dto.payment;

import java.time.LocalDateTime;
import java.util.Objects;

public class SubscriptionPurchaseDTO {

    private String id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private Boolean successful;

    private Integer paymentOption;

    private Long subscription;

    private Double amount;

    private String currency;

    public SubscriptionPurchaseDTO() {
    }

    public SubscriptionPurchaseDTO(String user, String id, LocalDateTime timeOfPurchase, Boolean successful, Integer paymentOption, Long subscription, Double amount, String currency) {
        this.user = user;
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.successful = successful;
        this.paymentOption = paymentOption;
        this.subscription = subscription;
        this.amount = amount;
        this.currency = currency;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
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
                Objects.equals(user, that.user) &&
                Objects.equals(timeOfPurchase, that.timeOfPurchase) &&
                Objects.equals(successful, that.successful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(subscription, that.subscription) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, id, timeOfPurchase, successful, paymentOption, subscription, amount, currency);
    }

    @Override
    public String toString() {
        return "SubscriptionPurchaseDTO{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", timeOfPurchase=" + timeOfPurchase +
                ", successful=" + successful +
                ", paymentOption=" + paymentOption +
                ", subscription=" + subscription +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
