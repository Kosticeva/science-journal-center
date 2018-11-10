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

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User userData;

    @Column(name = "TITLE", length = 31, nullable = false)
    private String title;

    @ManyToMany
    @JoinTable(name = "REVIEWER_MAGAZINE", joinColumns = @JoinColumn(name = "REVIEWER_ID"), inverseJoinColumns = @JoinColumn(name = "MAGAZINE_ISSN"))
    private Set<Magazine> magazines = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "REVIEWER_FIELD", joinColumns = @JoinColumn(name = "REVIEWER_ID"), inverseJoinColumns = @JoinColumn(name="FIELD_CODE"))
    private Set<ScienceField> fields = new HashSet<>();
}
