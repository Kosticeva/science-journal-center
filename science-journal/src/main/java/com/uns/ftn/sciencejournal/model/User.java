package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    private String username;

    @Column(name = "FIRST_NAME", length = 31, nullable = true)
    private String fName;

    @Column(name = "LAST_NAME", length = 31, nullable = true)
    private String lName;

    @Column(name = "CITY", length = 31, nullable = true)
    private String city;

    @Column(name = "COUNTRY", length = 31, nullable = true)
    private String country;

    @Column(name = "EMAIL", length = 63, nullable = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "USERNAME")
    private Credentials credentials;

    /*@OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_TRANSACTIONS", joinColumns = @JoinColumn(name = "USERNAME"), inverseJoinColumns = @JoinColumn(name = "TRANSACTION_ID"))
    private Set<Transaction> transactions = new HashSet<>();*/
}
