package com.uns.ftn.sciencejournal.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class PaperDTO {

    private String doi;

    private String title;

    private String paperAbstract;

    private String keyTerms;

    private String author;

    private Long issue;

    private String field;

    private Double price;

    @JsonIgnore
    private Byte[] file;

    private Set<Long> coauthors;

    public PaperDTO() {
    }

    public PaperDTO(String doi, String title, String paperAbstract, String keyTerms, String author, Long issue, String field, Double price, Byte[] file, Set<Long> coauthors) {
        this.doi = doi;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.author = author;
        this.issue = issue;
        this.field = field;
        this.price = price;
        this.file = file;
        this.coauthors = coauthors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperDTO paperDTO = (PaperDTO) o;
        return Objects.equals(doi, paperDTO.doi) &&
                Objects.equals(title, paperDTO.title) &&
                Objects.equals(paperAbstract, paperDTO.paperAbstract) &&
                Objects.equals(keyTerms, paperDTO.keyTerms) &&
                Objects.equals(author, paperDTO.author) &&
                Objects.equals(issue, paperDTO.issue) &&
                Objects.equals(field, paperDTO.field) &&
                Objects.equals(price, paperDTO.price) &&
                Arrays.equals(file, paperDTO.file) &&
                Objects.equals(coauthors, paperDTO.coauthors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(doi, title, paperAbstract, keyTerms, author, issue, field, price, coauthors);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaperAbstract() {
        return paperAbstract;
    }

    public void setPaperAbstract(String paperAbstract) {
        this.paperAbstract = paperAbstract;
    }

    public String getKeyTerms() {
        return keyTerms;
    }

    public void setKeyTerms(String keyTerms) {
        this.keyTerms = keyTerms;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getIssue() {
        return issue;
    }

    public void setIssue(Long issue) {
        this.issue = issue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public Set<Long> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<Long> coauthors) {
        this.coauthors = coauthors;
    }
}
