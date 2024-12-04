package com.liceu.sistem_evidenta_elevi.note_elevi.entity;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idElev;

    @Column(nullable = false)
    private Long idmaterie;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private LocalDate date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdElev() {
        return idElev;
    }

    public void setidElev(Long idElev) {
        this.idElev = idElev;
    }

    public Long getidmaterie() {
        return idmaterie;
    }

    public void setidmaterie(Long idmaterie) {
        this.idmaterie = idmaterie;
    }

    public Integer getnota() {
        return nota;
    }

    public void setnota(Integer nota) {
        this.nota = nota;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
