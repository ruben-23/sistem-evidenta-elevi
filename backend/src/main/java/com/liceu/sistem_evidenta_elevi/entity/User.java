package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

/**
 * Entitate care reprezinta un utilizator al sistemului.
 * Aceasta este mapata la tabelul "users" din baza de date.
 */
@Entity
@Table(name="users")
public class User {

    /**
     * ID-ul unic al utilizatorului.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUser;

    /**
     * Numele de utilizator al utilizatorului.
     */
    @Column(nullable=false)
    private String username;

    /**
     * Parola utilizatorului.
     */
    @Column(nullable=false)
    private String parola;

    /**
     * Adresa de email a utilizatorului.
     */
    @Column(nullable = false)
    private String email;

    /**
     * Rolul utilizatorului.
     * Acesta este de tip {@link Rol}.
     */
    @Enumerated(EnumType.STRING)
    private Rol rol;

    /**
     * Profesorul asociat utilizatorului.
     * Relatie de tip One-to-One cu entitatea {@link Profesor}.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Profesor profesor;

    /**
     * Secretara asociata utilizatorului.
     * Relatie de tip One-to-One cu entitatea {@link Secretara}.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Secretara secretara;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Secretara getSecretara() {
        return secretara;
    }

    public void setSecretara(Secretara secretara) {
        this.secretara = secretara;
    }
}
