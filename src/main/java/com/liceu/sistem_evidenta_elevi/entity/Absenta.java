package com.liceu.sistem_evidenta_elevi.entity;


import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="absente")
public class Absenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbsenta;

    @Column(nullable = false)
    private LocalDate data;

    // elev caruia ii apartine absenta
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idElev")
    private Elev elev;

    // materia la care a fost absent
    @ManyToOne
    @JoinColumn(name = "id_materie", referencedColumnName = "idMaterie")
    private Materie materie;



}