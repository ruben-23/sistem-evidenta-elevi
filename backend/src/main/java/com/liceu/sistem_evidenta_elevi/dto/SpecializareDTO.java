package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de specializari si returnarea raspunsurilor.
 * Aceasta clasa este utilizata pentru a transfera date despre specializari intre diferite straturi ale aplicatiei.
 */
public class SpecializareDTO {

    private Integer idSpecializare;
    private String nume;

    public Integer getIdSpecializare() {
        return idSpecializare;
    }

    public void setIdSpecializare(Integer idSpecializare) {
        this.idSpecializare = idSpecializare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume=nume;
}
}