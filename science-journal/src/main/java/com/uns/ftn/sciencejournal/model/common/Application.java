package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.User;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "APPLICATION")
public class Application {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long paperId;

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
    @JoinTable(name = "COAUTHORS", joinColumns = @JoinColumn(name = "PAPER_ID"), inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private Set<User> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState state;

    @Column(name = "ACCEPTED")
    private Boolean accepted;

    public Application() {
    }

    public Application(String title, String paperAbstract, String keyTerms, Credentials author, Set<User> coauthors, Magazine magazine, ScienceField field, Byte[] file, PaperApplicationState state, Boolean accepted) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.author = author;
        this.coauthors = coauthors;
        this.magazine = magazine;
        this.field = field;
        this.file = file;
        this.state = state;
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(paperId, that.paperId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(paperAbstract, that.paperAbstract) &&
                Objects.equals(keyTerms, that.keyTerms) &&
                Objects.equals(author, that.author) &&
                Objects.equals(coauthors, that.coauthors) &&
                Objects.equals(magazine, that.magazine) &&
                Objects.equals(field, that.field) &&
                Arrays.equals(file, that.file) &&
                state == that.state &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(paperId, title, paperAbstract, keyTerms, author, coauthors, magazine, field, state, accepted);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    @Override
    public String toString() {
        return "Application{" +
                "paperId=" + paperId +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", author=" + author +
                ", coauthors=" + coauthors +
                ", magazine=" + magazine +
                ", field=" + field +
                ", file=" + Arrays.toString(file) +
                ", state=" + state +
                ", accepted=" + accepted +
                '}';
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
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

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
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

    public PaperApplicationState getState() {
        return state;
    }

    public void setState(PaperApplicationState state) {
        this.state = state;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
