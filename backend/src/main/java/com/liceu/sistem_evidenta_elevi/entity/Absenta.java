package com.liceu.sistem_evidenta_elevi.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entitate care reprezinta o absenta a unui elev.
 * Aceasta este mapata la tabelul "absente" din baza de date.
 */
@Entity
@Table(name="absente")
public class Absenta {

    /**
     * ID-ul unic al absentei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbsenta;

    /**
     *Data in care a fost primita absenta.
     */
    @Column(nullable = false)
    private LocalDate data;

    /**
     * Elevul care a primit absenta.
     * Relatie de tip Many-to-One cu entitatea {@link Elev}.
     */
    // elev caruia ii apartine absenta
    @ManyToOne
    @JoinColumn(name = "id_elev", referencedColumnName = "idElev")
    private Elev elev;

    /**
     * Materia la care s-a primit cu absenta.
     * Relatie de tip Many-to-One cu entitatea {@link Materie}.
     */
    // materia la care a fost absent
    @ManyToOne
    @JoinColumn(name = "id_materie", referencedColumnName = "idMaterie")
    private Materie materie;

    /**
     * Modulul in care a fost primita absenta.
     */
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