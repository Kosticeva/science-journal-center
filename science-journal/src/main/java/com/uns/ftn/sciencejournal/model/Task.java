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

    @ManyToOne
    private User user;

    @Column(name = "DEADLINE", nullable = false)
    private LocalDate deadline;

    @ManyToOne
    private Paper paper;

    @Column(name = "TASK_SUMMARY", nullable = false)
    private String taskSummary;

    @Column(name = "TASK_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaperApplicationState type;

    @Column(name = "FINISHED", nullable = true)
    private Boolean finished;
}
