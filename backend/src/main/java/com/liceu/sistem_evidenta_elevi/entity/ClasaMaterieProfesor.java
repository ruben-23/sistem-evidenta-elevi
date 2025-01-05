package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

/**
 * Entitate care reprezintă relația dintre clase, profesori și materii.
 * Aceasta este mapată la tabelul "clase_materii_profesori" din baza de date.
 */
@Entity
@Table(name = "clase_materii_profesori")
@IdClass(ClasaMaterieProfesorId.class)
public class ClasaMaterieProfesor {

    /**
     * ID-ul clasei.
     * Parte a cheii primare compuse.
     */
    @Id
    @Column(name = "id_clasa")
    private Integer idClasa;

    /**
     * ID-ul profesorului.
     * Parte a cheii primare compuse.
     */
    @Id
    @Column(name = "id_profesor")
    private Integer idProfesor;

    /**
     * ID-ul materiei.
     * Parte a cheii primare compuse.
     */
    @Id
    @Column(name = "id_materie")
    private Integer idMaterie;

    /**
     * Clasa asociată.
     * Relație de tip Many-to-One cu entitatea {@link Clasa}.
     */
    @ManyToOne
    @JoinColumn(name="id_clasa", referencedColumnName = "idClasa")
    private Clasa clasa;

    /**
     * Profesorul asociat.
     * Relație de tip Many-to-One cu entitatea {@link Profesor}.
     */
    @ManyToOne
    @JoinColumn(name="id_profesor", referencedColumnName = "idProfesor")
    private Profesor profesor;

    /**
     * Materia asociată.
     * Relație de tip Many-to-One cu entitatea {@link Materie}.
     */
    @ManyToOne
    @JoinColumn(name="id_materie", referencedColumnName = "idMaterie")
    private Materie materie;

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Materie getMaterie() {
        return materie;
    }

    public void setMaterie(Materie materie) {
        this.materie = materie;
    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
    }
}
