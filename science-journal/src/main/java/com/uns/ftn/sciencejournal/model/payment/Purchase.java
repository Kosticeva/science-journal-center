package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDateTime timeOfPurchase;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER")
    private Credentials user;

    @Column(name = "MERCHANDISE", nullable = false)
    private PurchaseType type;

    @Column(name = "SUCCESSFUL")
    private Boolean successful;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAYMENT_OPTION", nullable = false)
    private PaymentOption option;

    public Purchase() {
    }

    public Purchase(LocalDateTime timeOfPurchase, Credentials user, PurchaseType type, Boolean successful, PaymentOption option) {
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.type = type;
        this.successful = successful;
        this.option = option;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTimeOfPurchase() {
        return timeOfPurchase;
    }

    public void setTimeOfPurchase(LocalDateTime timeOfPurchase) {
        this.timeOfPurchase = timeOfPurchase;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
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

    public PaymentOption getOption() {
        return option;
    }

    public void setOption(PaymentOption option) {
        this.option = option;
    }
}
