package com.uns.ftn.sciencejournal.dto.payment;

import com.uns.ftn.sciencejournal.model.enums.SubscriptionType;

import java.time.LocalDate;
import java.util.Objects;

public class SubscriptionDTO {

    private Long id;

    private String user;

    private String magazine;

    private SubscriptionType type;

    private LocalDate date;

    private Boolean paid;

    private Boolean cancelled;

    public SubscriptionDTO() {
    }

    public SubscriptionDTO(Long id, String user, String magazine, SubscriptionType type, LocalDate date, Boolean paid, Boolean cancelled) {
        this.id = id;
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
        SubscriptionDTO that = (SubscriptionDTO) o;
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
        return Objects.hash(id, user, magazine, type, date, paid, cancelled);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
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
}
