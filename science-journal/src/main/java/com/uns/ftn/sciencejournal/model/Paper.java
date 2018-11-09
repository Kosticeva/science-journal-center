package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PAPER")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PAPER_COAUTHORS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "USERNAME"))
    private Set<User> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "POSTED_BY", nullable = false)
    private User author;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "DOI", nullable = true)
    private String doi;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "RELEVANT", nullable = true)
    private Boolean relevant;

    @Column(name = "PROPER_CONSTRUCT", nullable = true)
    private Boolean proper;

    @Column(name = "ACCEPTED", nullable = true)
    private Boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "MAGAZINE", nullable = false)
    private Magazine magazine;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PAPER_CORRECTIONS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "CORRECTION_ID"))
    private Set<Correction> corrections = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PAPER_COMMENTS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "COMMENT_ID"))
    private Set<Comment> comments = new HashSet<>();

}
