package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;

@Entity
@Table(name = "PAPER_PURCHASE")
public class PaperPurchase extends Purchase{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PAPER", nullable = false)
    private Paper paper;

}
