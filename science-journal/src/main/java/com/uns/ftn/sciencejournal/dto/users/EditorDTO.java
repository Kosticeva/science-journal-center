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

    public EditorDTO(String user, Integer id, String title, String magazine, String field) {
        this.user = user;
        this.id = id;
        this.title = title;
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
        return Objects.hash(user, id, title, magazine, field);
    }

    @Override
    public String toString() {
        return "EditorDTO{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
