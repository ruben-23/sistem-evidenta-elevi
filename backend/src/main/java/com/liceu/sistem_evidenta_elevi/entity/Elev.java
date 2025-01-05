package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entitate care reprezinta un elev.
 * Aceasta este mapata la tabelul "elevi" din baza de date.
 */
@Entity
@Table(name="elevi")
public class Elev {

    /**
     * ID-ul unic al elevului.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idElev;

    /**
     * Numele elevului.
     */
    @Column(nullable = false)
    private String nume;

    /**
     * Prenumele elevului.
     */
    @Column(nullable = false)
    private String prenume;

    /**
     * CNP-ul elevului.
     */
    @Column(nullable = false)
    private String CNP;

    /**
     * Sexul elevului.
     */
    @Column(nullable = false)
    private String sex;

    /**
     * Numarul de telefon al elevului.
     */
    private String numarTelefon;

    /**
     * Adresa elevului.
     */
    @Column(nullable = false)
    private String adresa;

    /**
     * Data nasterii a elevului.
     */
    @Column(nullable = false)
    private LocalDate dataNasterii;

    /**
     * Clasa din care face parte elevul.
     * Relatie de tip Many-to-One cu entitatea {@link Clasa}.
     */
    // clasa din care face parte elevul
    @ManyToOne
    @JoinColumn(name="id_clasa", referencedColumnName = "idClasa")
    private Clasa clasa;

    /**
     * Notele primite de elev.
     * Relatie de tip One-to-Many cu entitatea {@link Nota}.
     */
    // notele primite de elev
    @OneToMany(mappedBy = "elev", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Nota> note = new ArrayList<>();

    /**
     * Absentele primite de elev.
     * Relatie de tip One-to-Many cu entitatea {@link Absenta}.
     */
    // absentele primite de elev
    @OneToMany(mappedBy = "elev", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Absenta> absente = new ArrayList<>();

    /**
     * Bursele de care beneficiaza elevul.
     * Relatie de tip Many-to-Many cu entitatea {@link Bursa}.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="elevi_burse",
            joinColumns = @JoinColumn(name = "id_elev"),
            inverseJoinColumns = @JoinColumn(name = "id_bursa")
    )
    private List<Bursa> burse = new ArrayList<>();

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

    public List<Bursa> getBurse() {
        return burse;
    }

    public void setBurse(List<Bursa> burse) {
        this.burse = burse;
    }
}
