package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;
import com.uns.ftn.sciencejournal.model.users.Credentials;
import com.uns.ftn.sciencejournal.model.users.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "APPLICATION")
public class Application {

    @EmbeddedId
    private ApplicationPK applicationPK;

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
            name = "COAUTHORS",
            joinColumns = {
                    @JoinColumn(name = "PAPER_ID", referencedColumnName = "ID"),
                    @JoinColumn(name = "PAPER_VERSION", referencedColumnName = "VERSION")},
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
    private Set<User> coauthors = new HashSet<>();

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

    @Column(name = "ACCEPTED", nullable = false)
    private Boolean accepted;

    @Column(name = "TIMESTAMP", nullable = false)
    private LocalDate timestamp;

    public Application() {
    }

    public Application(Long paperId, Integer version, String title, String paperAbstract, String keyTerms,
                       Credentials author, Set<User> coauthors, Magazine magazine, ScienceField field, String file,
                       PaperApplicationState state, Boolean accepted, LocalDate timestamp) {
        this.applicationPK = new ApplicationPK(paperId, version);
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
        this.timestamp = timestamp;
    }

    public ApplicationPK getApplicationPK() {
        return applicationPK;
    }

    public void setApplicationPK(ApplicationPK applicationPK) {
        this.applicationPK = applicationPK;
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
        Application that = (Application) o;
        return Objects.equals(applicationPK, that.applicationPK) &&
                Objects.equals(title, that.title) &&
                Objects.equals(paperAbstract, that.paperAbstract) &&
                Objects.equals(keyTerms, that.keyTerms) &&
                Objects.equals(author, that.author) &&
                Objects.equals(coauthors, that.coauthors) &&
                Objects.equals(magazine, that.magazine) &&
                Objects.equals(field, that.field) &&
                Objects.equals(file, that.file) &&
                state == that.state &&
                Objects.equals(accepted, that.accepted) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationPK, title, paperAbstract, keyTerms, author, coauthors, magazine, field,
                file, state, accepted, timestamp);
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationPK=" + applicationPK +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", author=" + author +
                ", coauthors=" + coauthors +
                ", magazine=" + magazine +
                ", field=" + field +
                ", file='" + file + '\'' +
                ", state=" + state +
                ", accepted=" + accepted +
                ", timestamp=" + timestamp +
                '}';
    }

    @Embeddable
    public class ApplicationPK implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        protected Long id;

        @Column(name = "VERSION")
        protected Integer version;

        public ApplicationPK() {
        }

        public ApplicationPK(Long id, Integer version) {
            this.id = id;
            this.version = version;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ApplicationPK that = (ApplicationPK) o;
            return Objects.equals(id, that.id) &&
                    Objects.equals(version, that.version);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, version);
        }
    }
}
