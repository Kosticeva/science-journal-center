package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.common.Issue;
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
    private Issue issue;

    public IssuePurchase() {
        super();
    }

    public IssuePurchase(String transactionId, LocalDateTime timeOfPurchase, Credentials user, PurchaseType type, Boolean successful, PaymentOption option, Double amount, String currency, Issue issue) {
        super(transactionId, timeOfPurchase, user, type, successful, option, amount, currency);
        this.issue = issue;
    }

    @Override
    public String toString() {
        return "IssuePurchase{" +
                "issue=" + issue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssuePurchase that = (IssuePurchase) o;
        return Objects.equals(issue, that.issue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issue);
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }
}
