package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class UserDTO {

    private Long id;

    private String fName;

    private String lName;

    private String city;

    private String country;

    private String email;
    
    private Double latitude;
    
    private Double longitude;

    public UserDTO() {
    }

    public UserDTO(Long id, String fName, String lName, String city, String country, String email, Double latitude, Double longitude) {
        this.id = id;
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
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(fName, userDTO.fName) &&
                Objects.equals(lName, userDTO.lName) &&
                Objects.equals(city, userDTO.city) &&
                Objects.equals(country, userDTO.country) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(latitude, userDTO.latitude) &&
                Objects.equals(longitude, userDTO.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, city, country, email, latitude, longitude);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
