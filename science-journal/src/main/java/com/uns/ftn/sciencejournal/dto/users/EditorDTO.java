package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class EditorDTO {

    private Integer id;

    private String title;

    private String user;

    private String magazine;

    private String field;

    public EditorDTO() {
    }

    public EditorDTO(Integer id, String title, String user, String magazine, String field) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.magazine = magazine;
        this.field = field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditorDTO editorDTO = (EditorDTO) o;
        return Objects.equals(id, editorDTO.id) &&
                Objects.equals(title, editorDTO.title) &&
                Objects.equals(user, editorDTO.user) &&
                Objects.equals(magazine, editorDTO.magazine) &&
                Objects.equals(field, editorDTO.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, user, magazine, field);
    }

    @Override
    public String toString() {
        return "EditorDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", magazine='" + magazine + '\'' +
                ", field='" + field + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
