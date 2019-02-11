package com.uns.ftn.sciencejournal.dto.payment;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaperPurchaseDTO {

    private String id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private Boolean successful;

    private Integer paymentOption;

    private String paper;

    private Double amount;

    private String currency;

    public PaperPurchaseDTO() {
    }

    public PaperPurchaseDTO(String id, LocalDateTime timeOfPurchase, String user, Boolean successful, Integer paymentOption, String paper, Double amount, String currency) {
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.successful = successful;
        this.paymentOption = paymentOption;
        this.paper = paper;
        this.amount = amount;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperPurchaseDTO that = (PaperPurchaseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeOfPurchase, that.timeOfPurchase) &&
                Objects.equals(user, that.user) &&
                Objects.equals(successful, that.successful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(paper, that.paper) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOfPurchase, user, successful, paymentOption, paper, amount, currency);
    }

    @Override
    public String toString() {
        return "PaperPurchaseDTO{" +
                "id='" + id + '\'' +
                ", timeOfPurchase=" + timeOfPurchase +
                ", user='" + user + '\'' +
                ", successful=" + successful +
                ", paymentOption=" + paymentOption +
                ", paper='" + paper + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}