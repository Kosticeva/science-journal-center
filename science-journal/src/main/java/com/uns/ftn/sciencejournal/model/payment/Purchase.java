package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Purchase {

    @Id
    @Column(name = "TRANSACTION_ID", length = 64)
    private String transactionId;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timeOfPurchase;

    /*@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER")
    private Credentials user;*/

    @Column(name = "TYPE")
    private PurchaseType type;

    @Column(name = "SUCCESSFUL")
    private Boolean successful;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_OPTION", nullable = false)
    private PaymentOption option;

    @Column(name = "AMOUNT", nullable = false)
    private Double amount;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    public Purchase() {
    }

    public Purchase(String transactionId, LocalDateTime timeOfPurchase, PurchaseType type, Boolean successful, PaymentOption option, Double amount, String currency) {
        this.transactionId = transactionId;
        this.timeOfPurchase = timeOfPurchase;
        this.type = type;
        this.successful = successful;
        this.option = option;
        this.amount = amount;
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
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

    public PaymentOption getOption() {
        return option;
    }

    public void setOption(PaymentOption option) {
        this.option = option;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
