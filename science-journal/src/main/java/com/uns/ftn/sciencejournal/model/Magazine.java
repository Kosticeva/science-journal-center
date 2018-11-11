package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MAGAZINE")
public class Magazine {

    @Id
    @Column(name = "ISSN", length = 8)
    private String issn;

    @Column(name = "NAME", length = 127, nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private MagazinePaymentType type;

    @Column(name = "MEMBERSHIP_PRICE", nullable = true)
    private Double membership;

    @OneToOne(optional = false)
    @JoinColumn(name = "CHIEF_EDITOR", unique = true)
    private Editor editor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MAGAZINE_FIELDS", joinColumns = @JoinColumn(name = "MAGAZINE"), inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> fields = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "MAGAZINE_PAYMENT_OPTIONS", joinColumns = @JoinColumn(name = "MAGAZINE"), inverseJoinColumns = @JoinColumn(name = "OPTION"))
    private Set<PaymentOption> options = new HashSet<>();
}
