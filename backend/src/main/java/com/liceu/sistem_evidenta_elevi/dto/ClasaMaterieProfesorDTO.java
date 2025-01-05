package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de relatiile dintre clase, profesori si materii.
 */
public class ClasaMaterieProfesorDTO {

    private Integer idClasa;
    private Integer idProfesor;
    private Integer idMaterie;

    public Integer getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(Integer idClasa) {
        this.idClasa = idClasa;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
    }
}
