package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


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