package com.liceu.sistem_evidenta_elevi.entity;

import com.liceu.sistem_evidenta_elevi.config.SecurityConfig;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clasa folosită ca și cheie primară compusă pentru entitatea {@link ClasaMaterieProfesor}.
 */
public class ClasaMaterieProfesorId extends SecurityConfig implements Serializable {

    /**
     * ID-ul clasei.
     */
    private Integer idClasa;

    /**
     * ID-ul profesorului.
     */
    private Integer idProfesor;

    /**
     * ID-ul materiei.
     */
    private Integer idMaterie;

    /**
     * Constructor implicit.
     */
    public ClasaMaterieProfesorId() {}

    /**
     * Constructor cu parametri.
     *
     * @param idClasa ID-ul clasei.
     * @param idProfesor ID-ul profesorului.
     * @param idMaterie ID-ul materiei.
     */
    public ClasaMaterieProfesorId(Integer idClasa, Integer idProfesor, Integer idMaterie) {
        this.idClasa = idClasa;
        this.idProfesor = idProfesor;
        this.idMaterie = idMaterie;
    }

    /**
     * Compară două entități de tip {@link ClasaMaterieProfesorId} în funcție de ID-urile cheii compuse.
     *
     * @param o Obiectul cu care se compară.
     * @return true dacă obiectele sunt egale, false în caz contrar.
     */
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

    /**
     * Generează un hash unic bazat pe ID-urile ce compun cheia.
     *
     * @return Hash-ul generat.
     */
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
