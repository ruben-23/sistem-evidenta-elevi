package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="note")
public class Nota {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idNota;

    @Column(name = "nota", nullable = false)
    private Double valoare;

    @Column(nullable = false)
    private LocalDate data;

    // elev caruia ii apartine nota
    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idElev")
    private Elev elev;

    // materia la care elevul a luat nota
    @ManyToOne
    @JoinColumn(name = "id_materie", referencedColumnName = "idMaterie")
    private Materie materie;

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

}
