package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="materii")
public class Materie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idMaterie;

    @Column(nullable = false)
    private String nume;

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
}
