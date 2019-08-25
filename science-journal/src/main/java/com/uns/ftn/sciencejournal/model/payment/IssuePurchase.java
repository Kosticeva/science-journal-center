package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.common.PaperIssue;
import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ISSUE_PURCHASE")
public class IssuePurchase extends Purchase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "ISSUE_MAGAZINE", referencedColumnName = "MAGAZINE"),
            @JoinColumn(name = "ISSUE_EDITION", referencedColumnName = "EDITION")
    })
    private PaperIssue paperIssue;

    public IssuePurchase() {
        super();
    }

    public IssuePurchase(Credentials user, String transactionId, LocalDateTime timeOfPurchase, PurchaseType type, Boolean successful, PaymentOption option, Double amount, String currency, PaperIssue paperIssue) {
        super(transactionId, timeOfPurchase, user, type, successful, option, amount, currency);
        this.paperIssue = paperIssue;
    }

    @Override
    public String toString() {
        return "IssuePurchase{" +
                "paperIssue=" + paperIssue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuePurchase that = (IssuePurchase) o;
        return Objects.equals(paperIssue, that.paperIssue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paperIssue);
    }

    public PaperIssue getPaperIssue() {
        return paperIssue;
    }

    public void setPaperIssue(PaperIssue paperIssue) {
        this.paperIssue = paperIssue;
    }
}
