package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REVIEWER")
public class Reviewer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USERNAME", unique = true)
    private Credentials user;

    @Column(name = "TITLE", length = 31, nullable = false)
    private String title;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_MAGAZINE", joinColumns = @JoinColumn(name = "REVIEWER"), inverseJoinColumns = @JoinColumn(name = "MAGAZINE"))
    private Set<Magazine> magazines = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_FIELD", joinColumns = @JoinColumn(name = "REVIEWER"), inverseJoinColumns = @JoinColumn(name="FIELD"))
    private Set<ScienceField> fields = new HashSet<>();
}
