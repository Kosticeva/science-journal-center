package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PAPER")
public class Paper {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long paperId;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @Column(name = "ABSTRACT", length = 1023, nullable = false)
    private String paperAbstract;

    @Column(name = "KEYWORDS", length = 1023, nullable = false)
    private String keyTerms;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PAPER_COAUTHORS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    private Set<User> coauthors = new HashSet<>();*/

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "POSTED_BY", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "MAGAZINE", nullable = false)
    private Magazine magazine;

    @ManyToOne(fetch = FetchType.LAZY)
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "DOI", nullable = true, unique = true)
    private String doi;

    @Column(name = "PRICE", nullable = true)
    private Double price;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState state;

    @Column(name = "ACCEPTED", nullable = true)
    private Boolean accepted;
}
