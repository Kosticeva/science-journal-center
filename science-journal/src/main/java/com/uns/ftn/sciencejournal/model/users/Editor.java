package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "EDITOR")
public class Editor {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USERNAME", unique = true)
    private Credentials user;

    @Column(name = "TITLE", length = 31, nullable = false)
    private String title;

    public Editor() {
    }

    public Editor(Credentials user, String title) {
        this.user = user;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Editor editor = (Editor) o;
        return Objects.equals(id, editor.id) &&
                Objects.equals(user, editor.user) &&
                Objects.equals(title, editor.title);
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

    public Credentials getUser() {
        return user;
    }

    public void setUser(Credentials user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
