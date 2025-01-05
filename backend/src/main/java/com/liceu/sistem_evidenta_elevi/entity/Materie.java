package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitate care reprezinta o materie.
 * Aceasta este mapata la tabelul "materii" din baza de date.
 */
@Entity
@Table(name="materii")
public class Materie {

    /**
     * ID-ul unic al materiei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idMaterie;

    /**
     * Numele materiei.
     */
    @Column(nullable = false)
    private String nume;

    /**
     * Notelor primite la materie.
     * Relatie de tip One-to-Many cu entitatea {@link Nota}.
     */
    // notele primite la materie
    @OneToMany(mappedBy = "materie")
    private List<Nota> note = new ArrayList<>();

    /**
     * Absentelor primite la materie.
     * Relatie de tip One-to-Many cu entitatea {@link Absenta}.
     */
    // absentele primite la materie
    @OneToMany(mappedBy = "materie")
    private List<Absenta> absente = new ArrayList<>();

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Nota> getNote() {
        return note;
    }

    public void setNote(List<Nota> note) {
        this.note = note;
    }

    public List<Absenta> getAbsente() {
        return absente;
    }

    public void setAbsente(List<Absenta> absente) {
        this.absente = absente;
    }
}
