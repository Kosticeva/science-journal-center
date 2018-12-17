package com.uns.ftn.sciencejournal.dto.common;

import java.time.LocalDate;
import java.util.Objects;

public class IssueDTO {

    private String magazine;

    private String edition;

    private LocalDate date;

    private Double price;

    public IssueDTO() {
    }

    public IssueDTO(String magazine, String edition, LocalDate date, Double price) {
        this.magazine = magazine;
        this.edition = edition;
        this.date = date;
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDTO issueDTO = (IssueDTO) o;
        return Objects.equals(magazine, issueDTO.magazine) &&
                Objects.equals(edition, issueDTO.edition) &&
                Objects.equals(date, issueDTO.date) &&
                Objects.equals(price, issueDTO.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazine, edition, date, price);
    }

    @Override
    public String toString() {
        return "IssueDTO{" +
                "magazine='" + magazine + '\'' +
                ", edition='" + edition + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}