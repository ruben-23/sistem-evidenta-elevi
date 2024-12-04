package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="permisiuni")
public class Permisiune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPermisiune;

    @Column(nullable=false)
    private String nume;

    @ManyToMany(mappedBy = "permisiuni")
    private Set<Rol> roluri = new HashSet<>();

    public int getIdPermisiune() {
        return idPermisiune;
    }

    public void setIdPermisiune(int idPermisiune) {
        this.idPermisiune = idPermisiune;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Set<Rol> getRoluri() {
        return roluri;
    }

    public void setRoluri(Set<Rol> roluri) {
        this.roluri = roluri;
    }
}
