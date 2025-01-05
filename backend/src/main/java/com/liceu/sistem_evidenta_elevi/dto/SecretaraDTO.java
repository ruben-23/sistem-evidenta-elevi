package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de secretare si returnarea raspunsurilor.
 * Aceasta clasa este utilizata pentru a transfera date despre secretare intre diferite straturi ale aplicatiei.
 */
public class SecretaraDTO {

    private Integer idSecretara;
    private String nume;
    private String prenume;
    private String numarTelefon;
    private String adresa;
    private String CNP;
    private Integer idUser;

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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
