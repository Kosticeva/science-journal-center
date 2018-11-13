package com.uns.ftn.sciencejournal.dto.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class ApplicationDTO {

    private Long paperId;

    private String title;

    private String paperAbstract;

    private String keyTerms;

    private String author;

    private String magazine;

    private String field;

    @JsonIgnore
    private Byte[] file;

    private PaperApplicationState state;

    private Boolean accepted;

    private Set<Long> coauthors;

    public ApplicationDTO() {
    }

    public ApplicationDTO(Long paperId, String title, String paperAbstract, String keyTerms, String author, String magazine, String field, Byte[] file, PaperApplicationState state, Boolean accepted, Set<Long> coauthors) {
        this.paperId = paperId;
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.keyTerms = keyTerms;
        this.author = author;
        this.magazine = magazine;
        this.field = field;
        this.file = file;
        this.state = state;
        this.accepted = accepted;
        this.coauthors = coauthors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationDTO that = (ApplicationDTO) o;
        return Objects.equals(paperId, that.paperId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(paperAbstract, that.paperAbstract) &&
                Objects.equals(keyTerms, that.keyTerms) &&
                Objects.equals(author, that.author) &&
                Objects.equals(magazine, that.magazine) &&
                Objects.equals(field, that.field) &&
                Arrays.equals(file, that.file) &&
                state == that.state &&
                Objects.equals(accepted, that.accepted) &&
                Objects.equals(coauthors, that.coauthors);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(paperId, title, paperAbstract, keyTerms, author, magazine, field, state, accepted, coauthors);
        result = 31 * result + Arrays.hashCode(file);
        return result;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public Set<Long> getCoauthors() {
        return coauthors;
    }

    public void setCoauthors(Set<Long> coauthors) {
        this.coauthors = coauthors;
    }
}
