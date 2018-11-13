package com.uns.ftn.sciencejournal.model.common;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ISSUE", uniqueConstraints = @UniqueConstraint(columnNames = {"MAGAZINE", "EDITION"}))
public class Issue {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @Column(name = "EDITION", nullable = false)
    private String edition;

    @Column(name = "PRINT_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    public Issue() {
    }

    public Issue(Magazine magazine, String edition, LocalDate date, Double price) {
        this.magazine = magazine;
        this.edition = edition;
        this.date = date;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(id, issue.id) &&
                Objects.equals(magazine, issue.magazine) &&
                Objects.equals(edition, issue.edition) &&
                Objects.equals(date, issue.date) &&
                Objects.equals(price, issue.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, magazine, edition, date, price);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", magazine=" + magazine +
                ", edition='" + edition + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
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
}
