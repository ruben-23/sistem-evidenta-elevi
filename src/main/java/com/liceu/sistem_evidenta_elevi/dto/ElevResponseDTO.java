package com.liceu.sistem_evidenta_elevi.dto;

import java.time.LocalDate;

public class ElevResponseDTO {

    private Integer idElev;
    private String nume;
    private String prenume;
    private String CNP;
    private String sex;
    private String numarTelefon;
    private String adresa;
    private LocalDate dataNasterii;
    private int idClasa;

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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(LocalDate dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public int getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(int idClasa) {
        this.idClasa = idClasa;
    }
}
