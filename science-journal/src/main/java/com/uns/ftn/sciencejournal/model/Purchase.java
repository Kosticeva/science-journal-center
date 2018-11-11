package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "TRANSACTION_ID")
    private Long transactionId;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDate timeOfPurchase;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER")
    private Credentials user;

    @Column(name = "MERCHANDISE", nullable = false)
    private PurchaseType type;

    @Column(name = "SUCCESSFUL", nullable = true)
    private Boolean successful;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "PAYMENT_OPTION", nullable = false)
    private PaymentOption option;

}
