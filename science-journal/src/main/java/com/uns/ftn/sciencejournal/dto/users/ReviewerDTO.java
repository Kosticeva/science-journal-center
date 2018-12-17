package com.uns.ftn.sciencejournal.dto.users;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ReviewerDTO {

    private Integer id;

    private String title;

    private String user;

    private Set<String> magazines = new HashSet<>();

    private Set<String> fields = new HashSet<>();

    public ReviewerDTO() {
    }

    public ReviewerDTO(Integer id, String title, String user, Set<String> magazines, Set<String> fields) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.magazines = magazines;
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewerDTO that = (ReviewerDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(user, that.user) &&
                Objects.equals(magazines, that.magazines) &&
                Objects.equals(fields, that.fields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, user, magazines, fields);
    }

    @Override
    public String toString() {
        return "ReviewerDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user='" + user + '\'' +
                ", magazines=" + magazines +
                ", fields=" + fields +
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
