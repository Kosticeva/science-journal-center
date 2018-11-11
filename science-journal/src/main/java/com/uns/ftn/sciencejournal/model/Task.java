package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.PaperApplicationState;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long taskId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ASSIGNEE")
    private Credentials user;

    @Column(name = "DEADLINE", nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "APPLICATION")
    private Application paper;

    @Column(name = "SUMMARY", nullable = false)
    private String taskSummary;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState type;

    @Column(name = "FINISHED", nullable = false)
    private Boolean finished;
}
