package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="USER")
public class User {

    @Id
    private String username;

    @Column(name="FIRST_NAME", length = 20, nullable = true)
    private String fName;

    @Column(name="LAST_NAME", length = 30, nullable = true)
    private String lName;

    @Column(name="CITY", length = 30, nullable = true)
    private String city;

    @Column(name="COUNTRY", length = 30, nullable = true)
    private String country;

    @Column(name="EMAIL", length = 30, nullable = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USERNAME")
    private Credentials credentials;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_TRANSACTIONS", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    private Set<Transaction> transactions = new HashSet<>();

    public User() {
    }

    public User(String username, String fName, String lName, String city, String country, String email, Credentials credentials, Set<Transaction> transactions) {
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.city = city;
        this.country = country;
        this.email = email;
        this.credentials = credentials;
        this.transactions = transactions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(fName, user.fName) &&
                Objects.equals(lName, user.lName) &&
                Objects.equals(city, user.city) &&
                Objects.equals(country, user.country) &&
                Objects.equals(email, user.email) &&
                Objects.equals(credentials, user.credentials) &&
                Objects.equals(transactions, user.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, fName, lName, city, country, email, credentials, transactions);
    }
}
