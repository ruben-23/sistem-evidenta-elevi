package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity

@Table(name="elevi")
public class Elev {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idElev;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(nullable = false)
    private String CNP;

    @Column(nullable = false)
    private String sex;

    private String numarTelefon;

    @Column(nullable = false)
    private String adresa;

    @Column(nullable = false)
    private LocalDate dataNasterii;

    // clasa din care face parte elevul
    @ManyToOne
    @JoinColumn(name="id_clasa", referencedColumnName = "idClasa")
    private Clasa clasa;

    // notele primite de elev
    @OneToMany(mappedBy = "elev", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Nota> note;

    // absentele primite de elev
    @OneToMany(mappedBy = "elev", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Absenta> absente;

    public Integer getIdElev() {
        return idElev;
    }

    public void setIdElev(Integer idElev) {
        this.idElev = idElev;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
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
