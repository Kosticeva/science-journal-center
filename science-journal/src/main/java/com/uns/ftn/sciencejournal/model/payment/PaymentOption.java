package com.uns.ftn.sciencejournal.model.payment;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PAYMENT_OPTION")
public class PaymentOption {

    @Id
    @GeneratedValue
    @Column(name = "CODE")
    private Integer paymentOptionCode;

    @Column(name = "NAME", nullable = false)
    private String paymentOptionName;

    public PaymentOption() {
    }

    public PaymentOption(Integer paymentOptionCode, String paymentOptionName) {
        this.paymentOptionCode = paymentOptionCode;
        this.paymentOptionName = paymentOptionName;
    }

    @Override
    public String toString() {
        return "PaymentOption{" +
                "paymentOptionCode=" + paymentOptionCode +
                ", paymentOptionName='" + paymentOptionName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentOption that = (PaymentOption) o;
        return Objects.equals(paymentOptionCode, that.paymentOptionCode) &&
                Objects.equals(paymentOptionName, that.paymentOptionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentOptionCode, paymentOptionName);
    }

    public Integer getPaymentOptionCode() {
        return paymentOptionCode;
    }

    public void setPaymentOptionCode(Integer paymentOptionCode) {
        this.paymentOptionCode = paymentOptionCode;
    }

    public String getPaymentOptionName() {
        return paymentOptionName;
    }

    public void setPaymentOptionName(String paymentOptionName) {
        this.paymentOptionName = paymentOptionName;
    }
}
