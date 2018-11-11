package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ISSUE")
public class Issue {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long issueID;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "EDITION", nullable = false)
    private String edition;

    @Column(name = "PRINT_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "PRICE", nullable = false)
    private Double price;
}
