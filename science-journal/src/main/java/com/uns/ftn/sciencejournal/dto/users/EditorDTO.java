package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class EditorDTO {

    private Integer id;

    private String user;

    private String title;

    public EditorDTO() {
    }

    public EditorDTO(Integer id, String user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditorDTO editorDTO = (EditorDTO) o;
        return Objects.equals(id, editorDTO.id) &&
                Objects.equals(user, editorDTO.user) &&
                Objects.equals(title, editorDTO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
