package com.liceu.sistem_evidenta_elevi.dto;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) pentru reprezentarea unei absente.
 * Aceasta clasa este utilizata pentru a transfera date despre absente intre diferite straturi ale aplicatiei.
 */
public class AbsentaDTO {

    /**
     * ID-ul unic al absentei.
     */
    private Integer idAbsenta;

    /**
     * ID-ul clasei in care e elevul..
     */
    private Integer idClasa;

    /**
     * ID-ul elevului care a primit absenta.
     */

    private Integer idElev;

    /**
     * ID-ul materiei la care s-a primit cu absenta.
     */
    private Integer idMaterie;

    /**
     * Data in care a fost primita absenta.
     */
    private LocalDate data;
    
    /**
     * Modulul in care a fost primita absenta.
     */
    private String modul;

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

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }
}