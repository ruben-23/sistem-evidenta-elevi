package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

/**
 * Entitate care reprezinta un profesor.
 * Aceasta este mapata la tabelul "profesori" din baza de date.
 */
@Entity
@Table(name="profesori")
public class Profesor {

    /**
     * ID-ul unic al profesorului.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idProfesor;

    /**
     * Numele profesorului.
     */
    @Column(nullable=false)
    private String nume;

    /**
     * Prenumele profesorului.
     */
    @Column(nullable=false)
    private String prenume;

    /**
     * Numarul de telefon al profesorului.
     */
    @Column(nullable=false)
    private String numarTelefon;

    /**
     * Adresa profesorului.
     */
    @Column(nullable=false)
    private String adresa;

    /**
     * CNP-ul profesorului.
     */
    @Column(nullable=false)
    private String CNP;

    /**
     * Utilizatorul asociat profesorului.
     * Relatie de tip One-to-One cu entitatea {@link User}.
     */
    // pentru a stii carui user este asociat profesorul
    @OneToOne
    @JoinColumn(name="id_user")
    private User user;

    /**
     * Clasa pentru care profesorul este diriginte.
     * Relatie de tip One-to-One cu entitatea {@link Clasa}.
     */
    // clasa pentru care profesorul e diriginte
    @OneToOne(mappedBy = "diriginte", cascade = CascadeType.ALL)
    private Clasa clasa;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
    }
}
