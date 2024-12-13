package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="specializari")
public class Specializare {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idSpecializare;

    @Column(nullable = false)
    private String nume;

    // clasele care au specializarea
    @OneToMany(mappedBy = "specializare")
    private Set<Clasa> clase;

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

    public Set<Clasa> getClase() {
        return clase;
    }

    public void setClase(Set<Clasa> clase) {
        this.clase = clase;
    }
}