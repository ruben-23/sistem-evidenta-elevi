package com.liceu.sistem_evidenta_elevi.entity;


import jakarta.persistence.*;

@Entity
@Table(name="secretare")
public class Secretara {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSecretara;

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
