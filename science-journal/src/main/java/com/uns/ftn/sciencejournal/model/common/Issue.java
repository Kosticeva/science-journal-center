package com.uns.ftn.sciencejournal.model.common;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ISSUE", uniqueConstraints = @UniqueConstraint(columnNames = {"MAGAZINE", "EDITION"}))
public class Issue {

    @EmbeddedId
    public IssuePK issuePK;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    @MapsId("ISSN")
    private Magazine magazine;

    @Column(name = "PRINT_DATE", nullable = false)
    private LocalDate date;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    public Issue() {
    }

    public Issue(IssuePK issuePK, Magazine magazine, LocalDate date, Double price) {
        this.issuePK = issuePK;
        this.magazine = magazine;
        this.date = date;
        this.price = price;
    }

    public IssuePK getIssuePK() {
        return issuePK;
    }

    public void setIssuePK(IssuePK issuePK) {
        this.issuePK = issuePK;
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
        return Objects.equals(issuePK, issue.issuePK) &&
                Objects.equals(magazine, issue.magazine) &&
                Objects.equals(date, issue.date) &&
                Objects.equals(price, issue.price);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issuePK=" + issuePK +
                ", magazine=" + magazine +
                ", date=" + date +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(issuePK, magazine, date, price);
    }

    @Embeddable
    public class IssuePK implements Serializable {

        @Column(name = "ISSN")
        protected String issn;

        @Column(name = "EDITION")
        protected String edition;

        public IssuePK() {
        }

        public IssuePK(String issn, String edition) {
            this.issn = issn;
            this.edition = edition;
        }

        public String getIssn() {
            return issn;
        }

        public void setIssn(String issn) {
            this.issn = issn;
        }

        public String getEdition() {
            return edition;
        }

        public void setEdition(String edition) {
            this.edition = edition;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IssuePK issuePK = (IssuePK) o;
            return Objects.equals(issn, issuePK.issn) &&
                    Objects.equals(edition, issuePK.edition);
        }

        @Override
        public int hashCode() {
            return Objects.hash(issn, edition);
        }
    }
}
