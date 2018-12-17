package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "FIRST_NAME", length = 31, nullable = false)
    private String fName;

    @Column(name = "LAST_NAME", length = 31, nullable = false)
    private String lName;

    @Column(name = "CITY", length = 31, nullable = false)
    private String city;

    @Column(name = "COUNTRY", length = 31, nullable = false)
    private String country;

    @Column(name = "EMAIL", length = 63, nullable = false)
    private String email;

    public User() {
    }

    public User(String fName, String lName, String city, String country, String email) {
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.country = country;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(fName, user.fName) &&
                Objects.equals(lName, user.lName) &&
                Objects.equals(city, user.city) &&
                Objects.equals(country, user.country) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fName, lName, city, country, email);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
