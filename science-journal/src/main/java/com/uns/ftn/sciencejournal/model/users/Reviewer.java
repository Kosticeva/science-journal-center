package com.uns.ftn.sciencejournal.model.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
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
    @JoinTable(name = "REVIEWER_FIELD", joinColumns = @JoinColumn(name = "REVIEWER"), inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> fields = new HashSet<>();

    public Reviewer() {
    }

    public Reviewer(Credentials user, String title, Set<Magazine> magazines, Set<ScienceField> fields) {
        this.user = user;
        this.title = title;
        this.magazines = magazines;
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", magazines=" + magazines +
                ", fields=" + fields +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer = (Reviewer) o;
        return Objects.equals(id, reviewer.id) &&
                Objects.equals(user, reviewer.user) &&
                Objects.equals(title, reviewer.title) &&
                Objects.equals(magazines, reviewer.magazines) &&
                Objects.equals(fields, reviewer.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, magazines, fields);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Magazine> getMagazines() {
        return magazines;
    }

    public void setMagazines(Set<Magazine> magazines) {
        this.magazines = magazines;
    }

    public Set<ScienceField> getFields() {
        return fields;
    }

    public void setFields(Set<ScienceField> fields) {
        this.fields = fields;
    }
}
