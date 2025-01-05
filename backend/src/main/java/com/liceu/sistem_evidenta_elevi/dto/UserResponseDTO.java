package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru returnarea raspunsurilor legate de utilizatori.
 * Acesta include informatiile de baza ale utilizatorului si
 * detalii despre profesorul sau secretara asociat contului.
 */
public class UserResponseDTO {
    private Integer idUser ;
    private String username;
    private String email;
    private String rol;
    private ProfesorDTO profesor; // pentru a transmite si informatiile despre profesor
    private SecretaraDTO secretara; // pentru a transmite si informatiile despre secretara

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfesorDTO getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public SecretaraDTO getSecretara() {
        return secretara;
    }

    public void setSecretara(SecretaraDTO secretara) {
        this.secretara = secretara;
    }
}
