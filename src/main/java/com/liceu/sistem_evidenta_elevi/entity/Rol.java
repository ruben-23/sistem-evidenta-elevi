package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="roluri")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(nullable=false)
    private String nume;

    // tabel pentru a reprezenta relatia de many-to-many dintre roluri si permisiuni
    @ManyToMany
    @JoinTable(
            name = "roluri_permisiuni",
            joinColumns = @JoinColumn(name="id_rol"),
            inverseJoinColumns = @JoinColumn(name="id_permisiune")
    )
    private Set<Permisiune> permisiuni;

    @OneToMany(mappedBy = "rol")
    private Set<User> useri = new HashSet<>();

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Set<Permisiune> getPermisiuni() {
        return permisiuni;
    }

    public void setPermisiuni(Set<Permisiune> permisiuni) {
        this.permisiuni = permisiuni;
    }

    public Set<User> getUseri() {
        return useri;
    }

    public void setUseri(Set<User> useri) {
        this.useri = useri;
    }
}
