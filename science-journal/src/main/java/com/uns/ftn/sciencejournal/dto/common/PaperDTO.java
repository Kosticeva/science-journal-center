package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.common.Application;
import com.uns.ftn.sciencejournal.model.common.Issue;

import java.util.Objects;
import java.util.Set;

public class PaperDTO {

    private String doi;

    private String title;

    private String paperAbstract;

    private String keyTerms;

    private String author;

    private Set<Long> coauthors;

    private Issue.IssuePK issue;

    private String field;

    private String file;

    private Double price;

    private Application.ApplicationPK lastRevision;

    public PaperDTO() {
    }

    public PaperDTO(String doi, String title, String paperAbstract, String keyTerms, String author,
                    Set<Long> coauthors, Issue.IssuePK issue, String field, String file, Double price,
                    Application.ApplicationPK lastRevision) {
        this.doi = doi;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.author = author;
        this.coauthors = coauthors;
        this.issue = issue;
        this.field = field;
        this.file = file;
        this.price = price;
        this.lastRevision = lastRevision;
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

    public Set<Long> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<Long> coauthors) {
        this.coauthors = coauthors;
    }

    public Issue.IssuePK getIssue() {
        return issue;
    }

    public void setIssue(Issue.IssuePK issue) {
        this.issue = issue;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Application.ApplicationPK getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(Application.ApplicationPK lastRevision) {
        this.lastRevision = lastRevision;
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
                Objects.equals(coauthors, paperDTO.coauthors) &&
                Objects.equals(issue, paperDTO.issue) &&
                Objects.equals(field, paperDTO.field) &&
                Objects.equals(file, paperDTO.file) &&
                Objects.equals(price, paperDTO.price) &&
                Objects.equals(lastRevision, paperDTO.lastRevision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi, title, paperAbstract, keyTerms, author, coauthors, issue,
                field, file, price, lastRevision);
    }

    @Override
    public String toString() {
        return "PaperDTO{" +
                "doi='" + doi + '\'' +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", author='" + author + '\'' +
                ", coauthors=" + coauthors +
                ", issue=" + issue +
                ", field='" + field + '\'' +
                ", file='" + file + '\'' +
                ", price=" + price +
                ", lastRevision=" + lastRevision +
                '}';
    }
}
