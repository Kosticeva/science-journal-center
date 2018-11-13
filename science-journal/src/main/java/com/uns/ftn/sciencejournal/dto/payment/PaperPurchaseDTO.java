package com.uns.ftn.sciencejournal.dto.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;

import java.time.LocalDateTime;
import java.util.Objects;

public class PaperPurchaseDTO {

    private Long id;

    private LocalDateTime timeOfPurchase;

    private String user;

    private PurchaseType type;

    private Boolean succesful;

    private Integer paymentOption;

    private String paper;

    public PaperPurchaseDTO() {
    }

    public PaperPurchaseDTO(Long id, LocalDateTime timeOfPurchase, String user, PurchaseType type, Boolean succesful, Integer paymentOption, String paper) {
        this.id = id;
        this.timeOfPurchase = timeOfPurchase;
        this.user = user;
        this.type = type;
        this.succesful = succesful;
        this.paymentOption = paymentOption;
        this.paper = paper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperPurchaseDTO that = (PaperPurchaseDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(timeOfPurchase, that.timeOfPurchase) &&
                Objects.equals(user, that.user) &&
                type == that.type &&
                Objects.equals(succesful, that.succesful) &&
                Objects.equals(paymentOption, that.paymentOption) &&
                Objects.equals(paper, that.paper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOfPurchase, user, type, succesful, paymentOption, paper);
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

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }
}
