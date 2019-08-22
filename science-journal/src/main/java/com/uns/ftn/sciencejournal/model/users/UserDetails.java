package com.uns.ftn.sciencejournal.model.users;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER")
public class UserDetails {

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
    
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;
    
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    public UserDetails() {
    }

    public UserDetails(Long userId, String fName, String lName, String city, String country, String email, Double latitude, Double longitude) {
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.country = country;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails userDetails = (UserDetails) o;
        return Objects.equals(userId, userDetails.userId) &&
                Objects.equals(fName, userDetails.fName) &&
                Objects.equals(lName, userDetails.lName) &&
                Objects.equals(city, userDetails.city) &&
                Objects.equals(country, userDetails.country) &&
                Objects.equals(email, userDetails.email) &&
                Objects.equals(latitude, userDetails.latitude) &&
                Objects.equals(longitude, userDetails.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, fName, lName, city, country, email);
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
