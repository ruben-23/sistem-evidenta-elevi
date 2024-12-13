package com.liceu.sistem_evidenta_elevi.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa folosita ca si cheie primara compusa pentru entitatea ClasaMaterieProfesor
 */

public class ClasaMaterieProfesorId implements Serializable {

    private Integer idClasa;
    private Integer idProfesor;
    private Integer idMaterie;

    public ClasaMaterieProfesorId() {}

    public ClasaMaterieProfesorId(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        this.idClasa = idClasa;
        this.idProfesor = idProfesor;
        this.idMaterie = idMaterie;
    }

    // pentru a compara 2 entitati de tip ClasaMaterieProfesor, in functie de id-urile cheii compuse
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // referinta catre acelasi obiect
        if (o == null || getClass() != o.getClass()) return false; // tipuri diferite

        ClasaMaterieProfesorId that = (ClasaMaterieProfesorId) o;
        return idClasa.equals(that.idClasa) &&
                idProfesor.equals(that.idProfesor) &&
                idMaterie.equals(that.idMaterie);
    }

    // pentru a genera un hash unic bazat pe id-urile ce compun cheia
    @Override
    public int hashCode() {
        return Objects.hash(idClasa, idProfesor, idMaterie);
    }

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
