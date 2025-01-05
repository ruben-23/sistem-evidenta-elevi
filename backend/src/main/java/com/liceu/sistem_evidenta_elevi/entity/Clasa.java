package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitate care reprezinta o clasa de elevi.
 * Aceasta este mapata la tabelul "clase" din baza de date.
 */
@Entity
@Table(name="clase")
public class Clasa {

    /**
     * ID-ul unic al clasei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idClasa;

    /**
     * Numele clasei.
     */
    @Column(nullable = false)
    private String nume;

    /**
     * Specializarea clasei.
     * Relatie de tip Many-to-One cu entitatea {@link Specializare}.
     */
    // specializarea clasei
    @ManyToOne
    @JoinColumn(name="id_specializare", referencedColumnName = "idSpecializare")
    private Specializare specializare;

    /**
     * Profesorul diriginte al clasei.
     * Relatie de tip One-to-One cu entitatea {@link Profesor}.
     */
    // indica profesorul care e diriginte al clasei
    @OneToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "idProfesor")
    private Profesor diriginte;

    /**
     * Lista elevilor din clasa.
     * Relatie de tip One-to-Many cu entitatea {@link Elev}.
     */
    // elevii din clasa
    @OneToMany (mappedBy = "clasa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Elev> elevi = new ArrayList<>();

    public Integer getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(Integer idClasa) {
        this.idClasa = idClasa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Specializare getSpecializare() {
        return specializare;
    }

    public void setSpecializare(Specializare specializare) {
        this.specializare = specializare;
    }

    public List<Elev> getElevi() {
        return elevi;
    }

    public void setElevi(List<Elev> elevi) {
        this.elevi = elevi;
    }

    public Profesor getDiriginte() {
        return diriginte;
    }

    public void setDiriginte(Profesor diriginte) {
        this.diriginte = diriginte;
    }
}
