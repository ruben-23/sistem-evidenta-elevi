package com.liceu.sistem_evidenta_elevi.dto;

import java.time.LocalDate;

public class AbsentaRequestDTO {

    private Integer idAbsenta;
    private Integer idClasa;
    private Integer idElev;
    private Integer idMaterie;
    private LocalDate data;

    public Integer getIdAbsenta() {
        return idAbsenta;
    }

    public void setIdAbsenta(Integer idAbsenta) {
        this.idAbsenta = idAbsenta;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
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
        this.idElev =idElev;
}
}