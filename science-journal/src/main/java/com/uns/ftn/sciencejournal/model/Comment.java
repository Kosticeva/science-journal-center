package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.ReviewSummary;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    private Long id;

    @OneToOne
    @MapsId
    private Task task;

    @Column(name = "PUBLIC_COMMENT", length = 1023, nullable = true)
    private String publicComment;

    @Column(name = "PRIVATE_COMMENT", length = 1023, nullable = true)
    private String privateComment;

    @Column(name = "REVIEW_SUMMARY", nullable = true)
    @Enumerated(EnumType.STRING)
    private ReviewSummary summary;
}
