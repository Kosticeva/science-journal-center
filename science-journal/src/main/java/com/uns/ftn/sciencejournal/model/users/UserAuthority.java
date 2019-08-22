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

    /*@OneToOne(optional = false)
    @JoinColumn(name = "USERNAME", unique = true)
    protected Credentials user;*/

    public UserAuthority() {
    }

    public UserAuthority(String title, Integer id) {
        this.title = title;
        this.id = id;
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

    @Override
    public String toString() {
        return "UserAuthority{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthority userAuthority = (UserAuthority) o;
        return Objects.equals(title, userAuthority.title) &&
                Objects.equals(id, userAuthority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}
