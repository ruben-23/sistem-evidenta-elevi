package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity

@Table(name="elevi")
public class Elev {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idElev;

    @Column(nullable = false)
    private String numeElev;

    @Column(nullable = false)
    private String prenumeElev;

    @Column(nullable = false)
    private String CNP;

    @Column(nullable = false)
    private String sexElev;

    @Column(nullable = false)
    private String numarTelefonElev;

    @Column(nullable = false)
    private String adresaElev;

    @Column(nullable = false)
    private Date dataNasteriiElev;


    public Integer getIdElev() {
        return idElev;
    }

    public void setIdElev(Integer idElev) {
        this.idElev = idElev;
    }

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }

    public String getPrenumeElev() {
        return prenumeElev;
    }

    public void setPrenumeElev(String prenumeElev) {
        this.prenumeElev = prenumeElev;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getSexElev() {
        return sexElev;
    }

    public void setSexElev(String sexElev) {
        this.sexElev = sexElev;
    }

    public String getNumarTelefonElev() {
        return numarTelefonElev;
    }

    public void setNumarTelefonElev(String numarTelefonElev) {
        this.numarTelefonElev = numarTelefonElev;
    }

    public String getAdresaElev() {
        return adresaElev;
    }

    public void setAdresaElev(String adresaElev) {
        this.adresaElev = adresaElev;
    }

    public Date getDataNasteriiElev() {
        return dataNasteriiElev;
    }

    public void setDataNasteriiElev(Date dataNasteriiElev) {
        this.dataNasteriiElev = dataNasteriiElev;
    }
}
