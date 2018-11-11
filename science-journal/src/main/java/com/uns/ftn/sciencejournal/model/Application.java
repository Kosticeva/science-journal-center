package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "APPLICATION")
public class Application {

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR")
    private Credentials author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COAUTHORS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private Set<User> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState state;

    @Column(name = "ACCEPTED", nullable = true)
    private Boolean accepted;
}
