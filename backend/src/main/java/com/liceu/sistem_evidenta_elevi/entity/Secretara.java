package com.liceu.sistem_evidenta_elevi.entity;


import jakarta.persistence.*;

/**
 * Entitate care reprezinta o secretara.
 * Aceasta este mapata la tabelul "secretare" din baza de date.
 */
@Entity
@Table(name="secretare")
public class Secretara {

    /**
     * ID-ul unic al secretarei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretara;

    /**
     * Numele secretarei.
     */
    @Column(nullable=false)
    private String nume;

    /**
     * Prenumele secretarei.
     */
    @Column(nullable=false)
    private String prenume;

    /**
     * Numarul de telefon al secretarei.
     */
    @Column(nullable=false)
    private String numarTelefon;

    /**
     * Adresa secretarei.
     */
    @Column(nullable=false)
    private String adresa;

    /**
     * CNP-ul secretarei.
     */
    @Column(nullable=false)
    private String CNP;

    /**
     * Utilizatorul asociat secretarei.
     * Relatie de tip One-to-One cu entitatea {@link User}.
     */
    // pentru a stii carui user este asociata secretara
    @OneToOne
    @JoinColumn(name="id_user")
    private User user;

    public Integer getIdSecretara() {
        return idSecretara;
    }

    public void setIdSecretara(Integer idSecretara) {
        this.idSecretara = idSecretara;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
