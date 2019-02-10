package com.uns.ftn.sciencejournal.dto.common;

import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;

import java.util.Objects;
import java.util.Set;

public class MagazineDTO {

    private String issn;

    private String name;

    private MagazinePaymentType type;

    private Double membership;

    private Integer editor;

    private Set<String> fields;

    private Set<Integer> options;

    private String currency;

    public MagazineDTO() {
    }

    public MagazineDTO(String issn, String name, MagazinePaymentType type, Double membership, Integer editor, Set<String> fields, Set<Integer> options, String currency) {
        this.issn = issn;
        this.name = name;
        this.type = type;
        this.membership = membership;
        this.editor = editor;
        this.fields = fields;
        this.options = options;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MagazineDTO that = (MagazineDTO) o;
        return Objects.equals(issn, that.issn) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(membership, that.membership) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(fields, that.fields) &&
                Objects.equals(options, that.options) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issn, name, type, membership, editor, fields, options, currency);
    }

    @Override
    public String toString() {
        return "MagazineDTO{" +
                "issn='" + issn + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", membership=" + membership +
                ", editor=" + editor +
                ", fields=" + fields +
                ", options=" + options +
                ", currency='" + currency + '\'' +
                '}';
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MagazinePaymentType getType() {
        return type;
    }

    public void setType(MagazinePaymentType type) {
        this.type = type;
    }

    public Double getMembership() {
        return membership;
    }

    public void setMembership(Double membership) {
        this.membership = membership;
    }

    public Integer getEditor() {
        return editor;
    }

    public void setEditor(Integer editor) {
        this.editor = editor;
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }

    public Set<Integer> getOptions() {
        return options;
    }

    public void setOptions(Set<Integer> options) {
        this.options = options;
    }
}
