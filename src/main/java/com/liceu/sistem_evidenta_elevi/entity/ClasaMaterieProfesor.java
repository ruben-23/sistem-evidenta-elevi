package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clase_materii_profesori")
@IdClass(ClasaMaterieProfesorId.class)
public class ClasaMaterieProfesor {

    @Id
    @Column(name = "id_clasa")
    private Integer idClasa;

    @Id
    @Column(name = "id_profesor")
    private Integer idProfesor;

    @Id
    @Column(name = "id_materie")
    private Integer idMaterie;

    @ManyToOne
    @JoinColumn(name="id_clasa", referencedColumnName = "idClasa")
    private Clasa clasa;

    @ManyToOne
    @JoinColumn(name="id_profesor", referencedColumnName = "idProfesor")
    private Profesor profesor;

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
