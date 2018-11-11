package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;

@Entity
@Table(name = "ISSUE_PURCHASE")
public class IssuePurchase extends Purchase{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ISSUE", nullable = false)
    private Issue issue;
}
