package com.liceu.sistem_evidenta_elevi.dto;

public class UserRequestDTO {

    private String username;
    private String parola;
    private String email;
    private String rol;

    // pentru a include informatii despre profesor(daca userul e profesor)
    private ProfesorDTO profesor;

    // alte variabile pentru celelalte tipuri de useri

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

    
}
