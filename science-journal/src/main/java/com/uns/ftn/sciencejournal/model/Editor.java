package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;

@Entity
@Table(name = "EDITOR")
public class Editor {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User userData;

    @Column(name = "TITLE", length = 31, nullable = false)
    private String title;
}
