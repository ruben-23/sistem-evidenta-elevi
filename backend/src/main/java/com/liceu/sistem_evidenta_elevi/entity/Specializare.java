package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitate care reprezinta o specializare a unei clase.
 * Aceasta este mapata la tabelul "specializari" din baza de date.
 */
@Entity
@Table(name="specializari")
public class Specializare {

    /**
     * ID-ul unic al specializarii.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idSpecializare;

    /**
     * Numele specializarii.
     */
    @Column(nullable = false)
    private String nume;

    /**
     * Clasele care au specializarea.
     * Relatie de tip One-to-Many cu entitatea {@link Clasa}.
     */
    // clasele care au specializarea
    @OneToMany(mappedBy = "specializare")
    private List<Clasa> clase = new ArrayList<>();

    public Integer getIdSpecializare() {
        return idSpecializare;
    }

    public void setIdSpecializare(Integer idSpecializare) {
        this.idSpecializare = idSpecializare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Clasa> getClase() {
        return clase;
    }

    public void setClase(List<Clasa> clase) {
        this.clase = clase;
    }
}