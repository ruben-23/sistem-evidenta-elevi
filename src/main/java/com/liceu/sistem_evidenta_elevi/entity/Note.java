package com.liceu.sistem_evidenta_elevi.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idElev;

    @Column(nullable = false)
    private Long idmaterie;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private LocalDate date;
