package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.users.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PAPER")
public class Paper {

    @Id
    @Column(name = "DOI", length = 20)
    private String doi;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "ABSTRACT", length = 1023, nullable = false)
    private String paperAbstract;

    @Column(name = "KEYWORDS", length = 1023, nullable = false)
    private String keyTerms;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR")
    private Credentials author;*/

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PAPER_COAUTHORS", joinColumns = @JoinColumn(name = "PAPER"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR"))
    private Set<UserDetails> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY/*, optional = false*/)
    @JoinColumn(name = "ISSUE")
    private PaperIssue paperIssue;

    @ManyToOne(fetch = FetchType.LAZY/*, optional = false*/)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private String file;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CURRENCY")
    private String currency;

    @OneToOne(/*optional = false*/)
    @JoinColumns({
            @JoinColumn(name = "LAST_REVISION_ID", referencedColumnName = "ID")})
    private PaperApplication lastRevision;

    public Paper() {
    }

    public Paper(String doi, String title, String paperAbstract, String keyTerms, Set<UserDetails> coauthors, PaperIssue paperIssue, ScienceField field, String file, Double price, String currency, PaperApplication lastRevision) {
        this.doi = doi;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.coauthors = coauthors;
        this.paperIssue = paperIssue;
        this.field = field;
        this.file = file;
        this.price = price;
        this.currency = currency;
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

    public Set<UserDetails> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<UserDetails> coauthors) {
        this.coauthors = coauthors;
    }

    public PaperIssue getPaperIssue() {
        return paperIssue;
    }

    public void setPaperIssue(PaperIssue paperIssue) {
        this.paperIssue = paperIssue;
    }

    public ScienceField getField() {
        return field;
    }

    public void setField(ScienceField field) {
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaperApplication getLastRevision() {
        return lastRevision;
    }

    public void setLastRevision(PaperApplication lastRevision) {
        this.lastRevision = lastRevision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return Objects.equals(doi, paper.doi) &&
                Objects.equals(title, paper.title) &&
                Objects.equals(paperAbstract, paper.paperAbstract) &&
                Objects.equals(keyTerms, paper.keyTerms) &&
                Objects.equals(coauthors, paper.coauthors) &&
                Objects.equals(paperIssue, paper.paperIssue) &&
                Objects.equals(field, paper.field) &&
                Objects.equals(file, paper.file) &&
                Objects.equals(price, paper.price) &&
                Objects.equals(currency, paper.currency) &&
                Objects.equals(lastRevision, paper.lastRevision);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi, title, paperAbstract, keyTerms, coauthors, paperIssue, field, file, price, currency, lastRevision);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "doi='" + doi + '\'' +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", coauthors=" + coauthors +
                ", paperIssue=" + paperIssue +
                ", field=" + field +
                ", file='" + file + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", lastRevision=" + lastRevision +
                '}';
    }
}
