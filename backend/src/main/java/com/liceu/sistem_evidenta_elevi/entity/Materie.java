package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="materii")
public class Materie {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idMaterie;

    @Column(nullable = false)
    private String nume;

    // notele primite la materie
    @OneToMany(mappedBy = "materie")
    private List<Nota> note = new ArrayList<>();

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
