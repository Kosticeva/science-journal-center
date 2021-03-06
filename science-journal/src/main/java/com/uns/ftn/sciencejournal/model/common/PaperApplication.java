package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "APPLICATION")
public class PaperApplication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VERSION")
    private Integer version;

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
    @JoinTable(
            name = "APPLICATION_COAUTHORS",
            joinColumns = {
                    @JoinColumn(name = "PAPER", referencedColumnName = "ID")},
            inverseJoinColumns = @JoinColumn(name = "AUTHOR", referencedColumnName = "ID"))
    private Set<UserDetails> coauthors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    @Column(name = "FILE", nullable = false)
    private String file;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState state;

    @Column(name = "ACCEPTED")
    private Boolean accepted;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDate timestamp;

    public PaperApplication() {
    }

    public PaperApplication(Credentials author, Long id, Integer version, String title, String paperAbstract, String keyTerms, Set<UserDetails> coauthors, Magazine magazine, ScienceField field, String file, PaperApplicationState state, Boolean accepted, LocalDate timestamp) {
        this.author = author;
        this.id = id;
        this.version = version;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.coauthors = coauthors;
        this.magazine = magazine;
        this.field = field;
        this.file = file;
        this.state = state;
        this.accepted = accepted;
        this.timestamp = timestamp;
    }

    public Credentials getAuthor() {
        return author;
    }

    public void setAuthor(Credentials author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
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

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperApplication that = (PaperApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(title, that.title) &&
                Objects.equals(paperAbstract, that.paperAbstract) &&
                Objects.equals(keyTerms, that.keyTerms) &&
                Objects.equals(coauthors, that.coauthors) &&
                Objects.equals(magazine, that.magazine) &&
                Objects.equals(field, that.field) &&
                Objects.equals(file, that.file) &&
                Objects.equals(author, that.author) &&
                state == that.state &&
                Objects.equals(accepted, that.accepted) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, id, version, title, paperAbstract, keyTerms, coauthors, magazine, field, file, state, accepted, timestamp);
    }

    @Override
    public String toString() {
        return "PaperApplication{" +
                "id=" + id +
                ", author=" + author +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", coauthors=" + coauthors +
                ", magazine=" + magazine +
                ", field=" + field +
                ", file='" + file + '\'' +
                ", state=" + state +
                ", accepted=" + accepted +
                ", timestamp=" + timestamp +
                '}';
    }
}
