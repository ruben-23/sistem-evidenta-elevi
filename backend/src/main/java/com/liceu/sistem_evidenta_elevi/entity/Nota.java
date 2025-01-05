package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entitate care reprezinta o nota.
 * Aceasta este mapata la tabelul "note" din baza de date.
 */
@Entity
@Table(name="note")
public class Nota {

    /**
     * ID-ul unic al notei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idNota;

    /**
     * Valoarea notei.
     */
    @Column(name = "nota", nullable = false)
    private Double valoare;

    /**
     * Data la care a fost pusa nota.
     */
    @Column(nullable = false)
    private LocalDate data;

    /**
     * Elevul caruia ii apartine nota.
     * Relatie de tip Many-to-One cu entitatea {@link Elev}.
     */
    // elev caruia ii apartine nota
    @ManyToOne
    @JoinColumn(name = "id_elev", referencedColumnName = "idElev")
    private Elev elev;

    /**
     * Materia la care elevul a luat nota.
     * Relatie de tip Many-to-One cu entitatea {@link Materie}.
     */
    // materia la care elevul a luat nota
    @ManyToOne
    @JoinColumn(name = "id_materie", referencedColumnName = "idMaterie")
    private Materie materie;

    /**
     * Modulul in care a fost acordata nota.
     */
    @Column(nullable = false)
    private String modul;

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Double getValoare() {
        return valoare;
    }

    public void setValoare(Double valoare) {
        this.valoare = valoare;
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
