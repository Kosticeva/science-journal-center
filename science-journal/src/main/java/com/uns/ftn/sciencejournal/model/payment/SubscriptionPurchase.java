package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.enums.PurchaseType;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SUBSCRIPTION_PURCHASE")
public class SubscriptionPurchase extends Purchase {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SUBSCRIPTION", nullable = false)
    private Subscription subscription;

    public SubscriptionPurchase() {
    }

    public SubscriptionPurchase(LocalDateTime timeOfPurchase, Credentials user, PurchaseType type, Boolean successful, PaymentOption option, Subscription subscription) {
        super(timeOfPurchase, user, type, successful, option);
        this.subscription = subscription;
    }

    @Override
    public String toString() {
        return "SubscriptionPurchase{" +
                "subscription=" + subscription +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionPurchase that = (SubscriptionPurchase) o;
        return Objects.equals(subscription, that.subscription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscription);
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
