package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PAPER")
public class Paper {

    @Id
    @Column(name = "DOI", unique = true)
    private String doi;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "ABSTRACT", length = 1023, nullable = false)
    private String paperAbstract;

    @Column(name = "KEYWORDS", length = 1023, nullable = false)
    private String keyTerms;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AUTHOR")
    private Credentials author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "COAUTHORS", joinColumns = @JoinColumn(name = "PAPER"), inverseJoinColumns = @JoinColumn(name = "AUTHOR"))
    private Set<User> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ISSUE")
    private Issue issue;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "PRICE")
    private Double price;

    public Paper() {
    }

    public Paper(String doi, String title, String paperAbstract, String keyTerms, Credentials author, Set<User> coauthors, Issue issue, ScienceField field, Byte[] file, Double price) {
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
                Objects.equals(author, paper.author) &&
                Objects.equals(coauthors, paper.coauthors) &&
                Objects.equals(issue, paper.issue) &&
                Objects.equals(field, paper.field) &&
                Arrays.equals(file, paper.file) &&
                Objects.equals(price, paper.price);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(doi, title, paperAbstract, keyTerms, author, coauthors, issue, field, price);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "doi='" + doi + '\'' +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", author=" + author +
                ", coauthors=" + coauthors +
                ", issue=" + issue +
                ", field=" + field +
                ", file=" + Arrays.toString(file) +
                ", price=" + price +
                '}';
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

    public Credentials getAuthor() {
        return author;
    }

    public void setAuthor(Credentials author) {
        this.author = author;
    }

    public Set<User> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<User> coauthors) {
        this.coauthors = coauthors;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ScienceField getField() {
        return field;
    }

    public void setField(ScienceField field) {
        this.field = field;
    }

    public Byte[] getFile() {
        return file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
