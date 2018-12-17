package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Authority {

    @Column(name = "TITLE", length = 31, nullable = false)
    protected String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USERNAME", unique = true)
    protected Credentials user;

    public Authority() {
    }

    public Authority(String title, Integer id, Credentials user) {
        this.title = title;
        this.id = id;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public String toString() {
        return "Authority{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(title, authority.title) &&
                Objects.equals(id, authority.id) &&
                Objects.equals(user, authority.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, user);
    }
}
