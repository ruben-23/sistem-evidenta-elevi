package com.liceu.sistem_evidenta_elevi.dto;

public class UserResponseDTO {
    private Integer idUser ;
    private String username;
    private String email;
    private ProfesorResponseDTO profesor; // pentru a transmite si informatiile despre profesor

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

    public ProfesorResponseDTO getProfesor() {
        return profesor;
    }

    public void setProfesor(ProfesorResponseDTO profesor) {
        this.profesor = profesor;
    }
}
