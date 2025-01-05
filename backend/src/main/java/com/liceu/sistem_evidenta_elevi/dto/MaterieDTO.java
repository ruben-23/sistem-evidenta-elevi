package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de materii si returnarea raspunsurilor.
 * Aceasta clasa este utilizata pentru a transfera date despre materii intre diferite straturi ale aplicatiei.
 */
public class MaterieDTO {
    private Integer idMaterie;
    private String nume;

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume=nume;
}
}