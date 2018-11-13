package com.uns.ftn.sciencejournal.dto.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import java.time.LocalDateTime;
import java.util.Objects;

public class SubscriptionPurchaseDTO {

    private Long id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private PurchaseType type;

    private Boolean succesful;

    private Integer paymentOption;

    private Long subscription;

    public SubscriptionPurchaseDTO() {
    }

    public SubscriptionPurchaseDTO(Long id, LocalDateTime timeOfPurchase, String user, PurchaseType type, Boolean succesful, Integer paymentOption, Long subscription) {
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.type = type;
        this.succesful = succesful;
        this.paymentOption = paymentOption;
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
                Objects.equals(succesful, that.succesful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(subscription, that.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOfPurchase, user, type, succesful, paymentOption, subscription);
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

    public Boolean getSuccesful() {
        return succesful;
    }

    public void setSuccesful(Boolean succesful) {
        this.succesful = succesful;
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
}
