package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.SubscriptionType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long subcriptionId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER")
    private Credentials user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "SUBSCRIPTION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    @Column(name = "VALID_UNTIL", nullable = false)
    private LocalDate date;

    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @Column(name = "CANCELLED", nullable = true)
    private Boolean cancelled;
}
