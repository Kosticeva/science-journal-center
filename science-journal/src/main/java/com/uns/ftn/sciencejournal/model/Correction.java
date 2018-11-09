package com.uns.ftn.sciencejournal.model;

import com.uns.ftn.sciencejournal.model.enums.CorrectionType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CORRECTION")
public class Correction {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "DEADLINE", nullable = false)
    private LocalDate date;

    @Column(name = "CHANGES", length = 1023, nullable = false)
    private String changes;

    @Column(name = "ACCEPTED", nullable = true)
    private Boolean accepted;

    @Column(name = "TYPE", nullable = false)
    private CorrectionType type;

    @Column(name = "FILE", nullable = false)
    private Byte[] file;

}
