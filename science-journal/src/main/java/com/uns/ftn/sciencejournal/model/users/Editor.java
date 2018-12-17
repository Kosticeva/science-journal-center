package com.uns.ftn.sciencejournal.model.users;

import com.uns.ftn.sciencejournal.model.common.Magazine;
import com.uns.ftn.sciencejournal.model.common.ScienceField;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Editor extends Authority {

    @ManyToOne
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    @ManyToOne(optional = true)
    @JoinColumn(name = "FIELD")
    private ScienceField field;

    public Editor() {
    }

    public Editor(String title, Integer id, Credentials user, Magazine magazine, ScienceField field) {
        super(title, id, user);
        this.magazine = magazine;
        this.field = field;
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

    @Override
    public String toString() {
        return "Editor{" +
                "magazine=" + magazine +
                ", field=" + field +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return magazine.equals(editor.magazine) &&
                id.equals(editor.id) &&
                title.equals(editor.title) &&
                user.equals(editor.user) &&
                Objects.equals(field, editor.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(magazine, field);
    }
}
