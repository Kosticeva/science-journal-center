package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class UserDTO {

    private Long id;

    private String fName;

    private String lName;

    private String city;

    private String country;

    private String email;

    public UserDTO() {
    }

    public UserDTO(Long id, String fName, String lName, String city, String country, String email) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.country = country;
        this.email = email;
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
                Objects.equals(email, userDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fName, lName, city, country, email);
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
                '}';
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
