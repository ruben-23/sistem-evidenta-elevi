package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de clase si returnarea raspunsurilor.
 * Aceasta clasa este utilizata pentru a transfera date despre clase intre diferite straturi ale aplicatiei.
 */
public class ClasaDTO {
    private Integer idClasa;
    private String nume;
    private Integer idSpecializare;
    private Integer idProfesor;

    public Integer getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(Integer idClasa) {
        this.idClasa = idClasa;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getIdSpecializare() {
        return idSpecializare;
    }

    public void setIdSpecializare(Integer idSpecializare) {
        this.idSpecializare = idSpecializare;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }
}
