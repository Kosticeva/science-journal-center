package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.ReviewType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long commentId;

    @Column(name = "REVIEW", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReviewType review;

    @Column(name = "COMMENT", length = 1023,nullable = false)
    private String comment;

    @Column(name = "EDITOR_COMMENT", length = 1023, nullable = true)
    private String editorComment;

    @Column(name = "DEADLINE", nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    //@Column(name = "REVIEWER", nullable = false)
    private Reviewer reviewer;

    //@OneToOne(fetch = FetchType.LAZY)
    //private Task task;
}
