package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PaperApplicationDTO {

    private Long paperId;

    private Integer version;

    private String title;

    private String paperAbstract;

    private String keyTerms;

    //private String author;

    private Set<Long> coauthors;

    private String magazine;

    private String field;

    private String file;

    private PaperApplicationState state;

    private Boolean accepted;

    private LocalDate timestamp;

    public PaperApplicationDTO() {
    }

    public PaperApplicationDTO(Long paperId, Integer version, String title, String paperAbstract, String keyTerms,
                               Set<Long> coauthors, String magazine, String field, String file,
                               PaperApplicationState state, Boolean accepted, LocalDate timestamp) {
        this.paperId = paperId;
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

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
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

    public Set<Long> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<Long> coauthors) {
        this.coauthors = coauthors;
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine;
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
    public String toString() {
        return "PaperApplicationDTO{" +
                "paperId=" + paperId +
                ", version=" + version +
                ", title='" + title + '\'' +
                ", paperAbstract='" + paperAbstract + '\'' +
                ", keyTerms='" + keyTerms + '\'' +
                ", coauthors=" + coauthors +
                ", magazine='" + magazine + '\'' +
                ", field='" + field + '\'' +
                ", file='" + file + '\'' +
                ", state=" + state +
                ", accepted=" + accepted +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperApplicationDTO that = (PaperApplicationDTO) o;
        return Objects.equals(paperId, that.paperId) &&
                Objects.equals(version, that.version) &&
                Objects.equals(title, that.title) &&
                Objects.equals(paperAbstract, that.paperAbstract) &&
                Objects.equals(keyTerms, that.keyTerms) &&
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
        return Objects.hash(paperId, version, title, paperAbstract, keyTerms, coauthors, magazine,
                field, file, state, accepted, timestamp);
    }
}
