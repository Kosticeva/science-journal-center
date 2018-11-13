package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;
import java.util.Set;

public class ReviewerDTO {

    private Integer id;

    private String user;

    private String title;

    private Set<String> magazines;

    private Set<String> fields;

    public ReviewerDTO() {
    }

    public ReviewerDTO(Integer id, String user, String title, Set<String> magazines, Set<String> fields) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.magazines = magazines;
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerDTO that = (ReviewerDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(title, that.title) &&
                Objects.equals(magazines, that.magazines) &&
                Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, title, magazines, fields);
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

    public Set<String> getMagazines() {
        return magazines;
    }

    public void setMagazines(Set<String> magazines) {
        this.magazines = magazines;
    }

    public Set<String> getFields() {
        return fields;
    }

    public void setFields(Set<String> fields) {
        this.fields = fields;
    }
}
