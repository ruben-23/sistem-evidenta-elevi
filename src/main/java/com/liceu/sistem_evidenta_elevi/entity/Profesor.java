package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="profesori")
public class Profesor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProfesor;

    @Column(nullable=false)
    private String nume;

    @Column(nullable=false)
    private String prenume;

    @Column(nullable=false)
    private String numarTelefon;

    @Column(nullable=false)
    private String adresa;

    @Column(nullable=false)
    private String CNP;

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
