package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CREDENTIALS")
public class Credentials {

    @Id
    @Column(name = "USERNAME", length = 31)
    private String username;

    @Column(name = "PASSWORD", length = 63, nullable = false)
    private String password;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DETAILS", unique = true)
    private UserDetails userDetails;

    public Credentials() {
    }

    public Credentials(String username, String password, UserDetails userDetails) {
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(userDetails, that.userDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userDetails);
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                '}';
    }
}
