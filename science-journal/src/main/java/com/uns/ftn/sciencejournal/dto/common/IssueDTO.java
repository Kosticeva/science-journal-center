package com.uns.ftn.sciencejournal.dto.common;

import java.time.LocalDate;
import java.util.Objects;

public class IssueDTO {

    private Long id;

    private String magazine;

    private String edition;

    private LocalDate date;

    private Double price;

    private String currency;

    public IssueDTO() {
    }

    public IssueDTO(Long id, String magazine, String edition, LocalDate date, Double price, String currency) {
        this.id = id;
        this.magazine = magazine;
        this.edition = edition;
        this.date = date;
        this.price = price;
        this.currency = currency;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDTO issueDTO = (IssueDTO) o;
        return Objects.equals(id, issueDTO.id) &&
                Objects.equals(magazine, issueDTO.magazine) &&
                Objects.equals(edition, issueDTO.edition) &&
                Objects.equals(date, issueDTO.date) &&
                Objects.equals(price, issueDTO.price) &&
                Objects.equals(currency, issueDTO.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, magazine, edition, date, price, currency);
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "id=" + id +
                ", magazine='" + magazine + '\'' +
                ", edition='" + edition + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}