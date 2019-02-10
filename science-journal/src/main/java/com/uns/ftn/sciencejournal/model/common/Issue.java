package com.uns.ftn.sciencejournal.model.common;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ISSUE", uniqueConstraints = @UniqueConstraint(columnNames = {"MAGAZINE", "EDITION"}))
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EDITION", length = 20)
    private String edition;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "PRINT_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    public Issue() {
    }

    public Issue(Long id, String edition, Magazine magazine, LocalDate date, Double price, String currency) {
        this.id = id;
        this.edition = edition;
        this.magazine = magazine;
        this.date = date;
        this.price = price;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id) &&
                Objects.equals(edition, issue.edition) &&
                Objects.equals(magazine, issue.magazine) &&
                Objects.equals(date, issue.date) &&
                Objects.equals(price, issue.price) &&
                Objects.equals(currency, issue.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edition, magazine, date, price, currency);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", edition='" + edition + '\'' +
                ", magazine=" + magazine +
                ", date=" + date +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
