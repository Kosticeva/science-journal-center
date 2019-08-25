package com.uns.ftn.sciencejournal.dto.users;

import java.util.Objects;

public class CredentialsDTO {

    private String username;

    private String password;

    private Long userDetails;

    public CredentialsDTO() {
    }

    public CredentialsDTO(String username, String password, Long userDetails) {
        this.username = username;
        this.password = password;
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredentialsDTO that = (CredentialsDTO) o;
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
        return "CredentialsDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userDetails=" + userDetails +
                '}';
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

    public Long getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(Long userDetails) {
        this.userDetails = userDetails;
    }
}