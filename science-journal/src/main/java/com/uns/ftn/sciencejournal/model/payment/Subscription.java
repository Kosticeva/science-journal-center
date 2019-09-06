package com.uns.ftn.sciencejournal.model.payment;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.enums.SubscriptionType;
import com.uns.ftn.sciencejournal.model.users.Credentials;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription implements Serializable {

    @Id
    @Column(name = "ID")
    private String id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER")
    private Credentials user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "SUBSCRIPTION_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private SubscriptionType type;

    @Column(name = "VALID_UNTIL", nullable = false)
    private LocalDate date;

    @Column(name = "PAID", nullable = false)
    private Boolean paid;

    @Column(name = "CANCELLED")
    private Boolean cancelled;

    public Subscription() {
    }

    public Subscription(Credentials user, Magazine magazine, SubscriptionType type, LocalDate date,
                        Boolean paid, Boolean cancelled) {
        this.user = user;
        this.magazine = magazine;
        this.type = type;
        this.date = date;
        this.paid = paid;
        this.cancelled = cancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(magazine, that.magazine) &&
                type == that.type &&
                Objects.equals(date, that.date) &&
                Objects.equals(paid, that.paid) &&
                Objects.equals(cancelled, that.cancelled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, magazine, type, date, paid, cancelled);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", magazine=" + magazine +
                ", type=" + type +
                ", date=" + date +
                ", paid=" + paid +
                ", cancelled=" + cancelled +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }
}
