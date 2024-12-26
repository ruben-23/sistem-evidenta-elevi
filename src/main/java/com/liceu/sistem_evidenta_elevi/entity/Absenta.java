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
    @JoinColumn(name = "id_elev", referencedColumnName = "idElev")
    private Elev elev;

    // materia la care a fost absent
    @ManyToOne
    @JoinColumn(name = "id_materie", referencedColumnName = "idMaterie")
    private Materie materie;

    @Column(nullable = false)
    private String modul;

    public Integer getIdAbsenta() {
        return idAbsenta;
    }

    public void setIdAbsenta(Integer idAbsenta) {
        this.idAbsenta = idAbsenta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Elev getElev() {
        return elev;
    }

    public void setElev(Elev elev) {
        this.elev = elev;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }
}