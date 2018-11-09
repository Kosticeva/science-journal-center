package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "REVIEWER")
public class Reviewer {

    @Id
    @Column(name="USERNAME")
    private String username;

    @Column(name="TITLE", length = 32, nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_MAGAZINES", joinColumns = @JoinColumn(name = "REVIEWER"), inverseJoinColumns = @JoinColumn(name = "MAGAZINES"))
    private Set<Magazine> magazines = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "REVIEWER")
    private User reviewer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "REVIEWER_FIELDS", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> scienceFields = new HashSet<>();

    public Reviewer() {
    }

    public Reviewer(String username, String title, Set<Magazine> magazines, User reviewer, Set<ScienceField> scienceFields) {
        this.username = username;
        this.title = title;
        this.magazines = magazines;
        this.reviewer = reviewer;
        this.scienceFields = scienceFields;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Set<ScienceField> getScienceFields() {
        return scienceFields;
    }

    public void setScienceFields(Set<ScienceField> scienceFields) {
        this.scienceFields = scienceFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviewer reviewer1 = (Reviewer) o;
        return Objects.equals(username, reviewer1.username) &&
                Objects.equals(title, reviewer1.title) &&
                Objects.equals(magazines, reviewer1.magazines) &&
                Objects.equals(reviewer, reviewer1.reviewer) &&
                Objects.equals(scienceFields, reviewer1.scienceFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, title, magazines, reviewer, scienceFields);
    }
}
