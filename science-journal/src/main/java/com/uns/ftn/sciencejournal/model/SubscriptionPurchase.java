package com.uns.ftn.sciencejournal.model;
import javax.persistence.*;

@Entity
@Table(name = "SUBSCRIPTION_PURCHASE")
public class SubscriptionPurchase extends Purchase{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SUBSCRIPTION", nullable = false)
    private Subscription subscription;

}
