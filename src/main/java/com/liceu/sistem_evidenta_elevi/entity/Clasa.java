package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="clase")
public class Clasa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idClasa;

    @Column(nullable = false)
    private String nume;

    // specializarea clasei
    @ManyToOne
    @JoinColumn(name="id_specializare", referencedColumnName = "idSpecializare")
    private Specializare specializare;

    // indica profesorul care e diriginte al clasei
    @OneToOne
    @JoinColumn(name = "profesor_id", referencedColumnName = "idProfesor")
    private Profesor diriginte;

    // elevii din clasa
    @OneToMany (mappedBy = "clasa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Elev> elevi;

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

    public Set<Elev> getElevi() {
        return elevi;
    }

    public void setElevi(Set<Elev> elevi) {
        this.elevi = elevi;
    }

    public Profesor getDiriginte() {
        return diriginte;
    }

    public void setDiriginte(Profesor diriginte) {
        this.diriginte = diriginte;
    }
}
