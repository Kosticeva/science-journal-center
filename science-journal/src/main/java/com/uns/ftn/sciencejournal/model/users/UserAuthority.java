package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class UserAuthority {

    @Column(name = "TITLE", length = 31, nullable = false)
    protected String title;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "USERNAME", unique = true)
    protected Credentials user;

    public UserAuthority() {
    }

    public UserAuthority(String title, Integer id, Credentials user) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthority that = (UserAuthority) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(id, that.id) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, user);
    }

    @Override
    public String toString() {
        return "UserAuthority{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", user=" + user +
                '}';
    }
}
