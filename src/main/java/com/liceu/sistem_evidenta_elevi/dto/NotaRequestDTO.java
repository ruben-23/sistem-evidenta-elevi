package com.liceu.sistem_evidenta_elevi.dto;

import java.time.LocalDate;

public class NotaRequestDTO {

    private Integer idNota;
    private Integer idClasa;
    private Integer idElev;
    private Integer idMaterie;
    private Double nota;
    private LocalDate data;

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getIdClasa() {
        return idClasa;
    }

    public void setIdClasa(Integer idClasa) {
        this.idClasa = idClasa;
    }

    public Integer getIdElev() {
        return idElev;
    }

    public void setIdElev(Integer idElev) {
        this.idElev = idElev;
    }

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
}
}