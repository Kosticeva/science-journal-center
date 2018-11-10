package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;

import javax.persistence.*;
import java.util.Objects;

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

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "MEMBERSHIP", nullable = true)
    private Double membership;

    @OneToOne
    @JoinColumn(name = "CHIEF_EDITOR")
    private Editor editor;

}
