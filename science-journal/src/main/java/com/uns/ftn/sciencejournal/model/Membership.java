package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="MEMBERSHIP")
public class Membership implements Serializable {

    @Id
    private String username;

    @Id
    private String issn;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USER")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "LAST_PAID", nullable = true)
    private LocalDate lastPayment;

    public Membership() {
    }

    public Membership(String username, String issn, User user, Magazine magazine, LocalDate lastPayment) {
        this.username = username;
        this.issn = issn;
        this.user = user;
        this.magazine = magazine;
        this.lastPayment = lastPayment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(LocalDate lastPayment) {
        this.lastPayment = lastPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membership that = (Membership) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(issn, that.issn) &&
                Objects.equals(user, that.user) &&
                Objects.equals(magazine, that.magazine) &&
                Objects.equals(lastPayment, that.lastPayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, issn, user, magazine, lastPayment);
    }
}
