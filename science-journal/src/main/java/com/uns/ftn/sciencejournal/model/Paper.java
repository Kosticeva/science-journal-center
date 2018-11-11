package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PAPER")
public class Paper {

    @Id
    @Column(name = "DOI", nullable = true, unique = true)
    private String doi;

    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;

    @Column(name = "ABSTRACT", length = 1023, nullable = false)
    private String paperAbstract;

    @Column(name = "KEYWORDS", length = 1023, nullable = false)
    private String keyTerms;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR")
    private Credentials author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COAUTHORS", joinColumns = @JoinColumn(name = "PAPER"), inverseJoinColumns = @JoinColumn(name = "AUTHOR"))
    private Set<User> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ISSUE")
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "PRICE", nullable = true)
    private Double price;
}
