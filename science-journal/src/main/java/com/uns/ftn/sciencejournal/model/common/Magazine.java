package com.uns.ftn.sciencejournal.model.common;

import com.uns.ftn.sciencejournal.model.enums.MagazinePaymentType;
import com.uns.ftn.sciencejournal.model.payment.PaymentOption;
import com.uns.ftn.sciencejournal.model.users.Editor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "MAGAZINE")
public class Magazine {

    @Id
    @Column(name = "ISSN", length = 8)
    private String issn;

    @Column(name = "NAME", length = 127, nullable = false)
    private String name;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private MagazinePaymentType type;

    @Column(name = "MEMBERSHIP_PRICE")
    private Double membership;

    @OneToOne(optional = false)
    @JoinColumn(name = "CHIEF_EDITOR", unique = true)
    private Editor editor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MAGAZINE_FIELDS", joinColumns = @JoinColumn(name = "MAGAZINE"), inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> fields = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "MAGAZINE_PAYMENT_OPTIONS", joinColumns = @JoinColumn(name = "MAGAZINE"), inverseJoinColumns = @JoinColumn(name = "OPTION"))
    private Set<PaymentOption> options = new HashSet<>();

    public Magazine() {
    }

    public Magazine(String issn, String name, MagazinePaymentType type, Double membership, Editor editor, Set<ScienceField> fields, Set<PaymentOption> options) {
        this.issn = issn;
        this.name = name;
        this.type = type;
        this.membership = membership;
        this.editor = editor;
        this.fields = fields;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return Objects.equals(issn, magazine.issn) &&
                Objects.equals(name, magazine.name) &&
                type == magazine.type &&
                Objects.equals(membership, magazine.membership) &&
                Objects.equals(editor, magazine.editor) &&
                Objects.equals(fields, magazine.fields) &&
                Objects.equals(options, magazine.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issn, name, type, membership, editor, fields, options);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "issn='" + issn + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", membership=" + membership +
                ", editor=" + editor +
                ", fields=" + fields +
                ", options=" + options +
                '}';
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

    public Editor getEditor() {
        return editor;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Set<ScienceField> getFields() {
        return fields;
    }

    public void setFields(Set<ScienceField> fields) {
        this.fields = fields;
    }

    public Set<PaymentOption> getOptions() {
        return options;
    }

    public void setOptions(Set<PaymentOption> options) {
        this.options = options;
    }
}
