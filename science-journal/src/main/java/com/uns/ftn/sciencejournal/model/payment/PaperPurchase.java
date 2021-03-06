package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.common.Paper;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PAPER_PURCHASE")
public class PaperPurchase extends Purchase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PAPER", nullable = false)
    private Paper paper;

    public PaperPurchase() {
        super();
    }

    public PaperPurchase(Credentials user, String transactionId, LocalDateTime timeOfPurchase, PurchaseType type, Boolean successful, PaymentOption option, Double amount, String currency, Paper paper) {
        super(transactionId, timeOfPurchase, user, type, successful, option, amount, currency);
        this.paper = paper;
    }

    @Override
    public String toString() {
        return "PaperPurchase{" +
                "paper=" + paper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperPurchase that = (PaperPurchase) o;
        return Objects.equals(paper, that.paper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paper);
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }
}
