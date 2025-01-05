package com.liceu.sistem_evidenta_elevi.dto;

/**
 * DTO pentru primirea cererilor legate de utilizatori.
 * Acesta contine informatiile necesare pentru a crea sau actualiza un utilizator,
 * inclusiv date despre profesor sau secretara, daca este cazul.
 */
public class UserRequestDTO {

    private Integer idUser;
    private String username;
    private String parola;
    private String email;
    private String rol;

    // pentru a include informatii despre profesor sau secretara
    private ProfesorDTO profesor;
    private SecretaraDTO secretara;

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

    public String getRol() {
        return rol;
    }

    public void setRol(String role) {
        this.rol = role;
    }

    public ProfesorDTO getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorDTO profesor) {
        this.profesor = profesor;
    }

    public SecretaraDTO getSecretara() {
        return secretara;
    }

    public void setSecretara(SecretaraDTO secretara) {
        this.secretara = secretara;
    }
}
