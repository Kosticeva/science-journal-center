package com.uns.ftn.sciencejournal.model;

import javax.persistence.*;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "TASK_ID")
    private Long id;
}
