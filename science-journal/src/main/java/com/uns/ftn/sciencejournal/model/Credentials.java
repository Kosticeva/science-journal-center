package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;

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
    private User userDetails;
}
