package com.uns.ftn.sciencejournal.model.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "REVIEWER")
public class Reviewer extends UserAuthority {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_MAGAZINE", joinColumns = @JoinColumn(name = "REVIEWER"),
            inverseJoinColumns = @JoinColumn(name = "MAGAZINE"))
    private Set<Magazine> magazines = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_FIELD", joinColumns = @JoinColumn(name = "REVIEWER"),
            inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> fields = new HashSet<>();

    public Reviewer() {
    }

    public Reviewer(String title, Integer id, Set<Magazine> magazines, Set<ScienceField> fields, Credentials user) {
        super(title, id, user);
        this.magazines = magazines;
        this.fields = fields;
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

    @Override
    public String toString() {
        return "Reviewer{" +
                "magazines=" + magazines +
                "user=" + user +
                ", fields=" + fields +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer = (Reviewer) o;
        return magazines.equals(reviewer.magazines) &&
                id.equals(reviewer.id) &&
                title.equals(reviewer.title) &&
                fields.equals(reviewer.fields) &&
                user.equals(reviewer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazines, fields, user);
    }
}
