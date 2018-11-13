package com.uns.ftn.sciencejournal.dto.common;

import java.time.LocalDate;
import java.util.Objects;

public class IssueDTO {

    private Long id;

    private String edition;

    private LocalDate date;

    private Double price;

    private String magazine;

    public IssueDTO() {
    }

    public IssueDTO(Long id, String edition, LocalDate date, Double price, String magazine) {
        this.id = id;
        this.edition = edition;
        this.date = date;
        this.price = price;
        this.magazine = magazine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IssueDTO issueDTO = (IssueDTO) o;
        return Objects.equals(id, issueDTO.id) &&
                Objects.equals(edition, issueDTO.edition) &&
                Objects.equals(date, issueDTO.date) &&
                Objects.equals(price, issueDTO.price) &&
                Objects.equals(magazine, issueDTO.magazine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, edition, date, price, magazine);
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

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
    }
}
