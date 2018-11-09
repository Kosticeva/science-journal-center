package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="EDITOR")
public class Editor {

    @Id
    @Column(name="USERNAME")
    private String username;

    @Column(name="TITLE", length = 32, nullable = false)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USER")
    private User editor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "EDITOR_FIELDS", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "FIELD"))
    private Set<ScienceField> scienceFields = new HashSet<>();

    public Editor() {
    }

    public Editor(String username, String title, Magazine magazine, User editor, Set<ScienceField> scienceFields) {
        this.username = username;
        this.title = title;
        this.magazine = magazine;
        this.editor = editor;
        this.scienceFields = scienceFields;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public User getEditor() {
        return editor;
    }

    public void setEditor(User editor) {
        this.editor = editor;
    }

    public Set<ScienceField> getScienceFields() {
        return scienceFields;
    }

    public void setScienceFields(Set<ScienceField> scienceFields) {
        this.scienceFields = scienceFields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor1 = (Editor) o;
        return Objects.equals(username, editor1.username) &&
                Objects.equals(title, editor1.title) &&
                Objects.equals(magazine, editor1.magazine) &&
                Objects.equals(editor, editor1.editor) &&
                Objects.equals(scienceFields, editor1.scienceFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, title, magazine, editor, scienceFields);
    }
}
