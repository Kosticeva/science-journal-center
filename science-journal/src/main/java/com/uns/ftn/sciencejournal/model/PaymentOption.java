package com.uns.ftn.sciencejournal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_OPTION")
public class PaymentOption {

    @Id
    @Column(name = "CODE")
    private Integer paymentOptionCode;

    @Column(name = "NAME", nullable = false)
    private String paymentOptionName;
}
