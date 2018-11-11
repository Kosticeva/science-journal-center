package com.uns.ftn.sciencejournal.model;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long userId;

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
}
