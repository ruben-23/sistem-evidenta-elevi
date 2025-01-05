package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entitate care reprezinta o bursa acordata elevilor.
 * Aceasta este mapata la tabelul "burse" din baza de date.
 */
@Entity
@Table(name="burse")
public class Bursa {

    /**
     * ID-ul unic al bursei.
     * Generat automat de baza de date.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idBursa;

    /**
     * Tipul bursei.
     */
    @Column(nullable=false)
    private String tip;

    /**
     * Suma acordata prin bursa.
     */
    @Column(nullable=false)
    private Integer suma;

    /**
     * Lista elevilor care beneficiaza de bursa.
     * Relatie de tip Many-to-Many cu entitatea {@link Elev}.
     */
    @ManyToMany(mappedBy = "burse")
    private List<Elev> elevi = new ArrayList<>();

    public Integer getIdBursa() {
        return idBursa;
    }

    public void setIdBursa(Integer idBursa) {
        this.idBursa = idBursa;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Integer getSuma() {
        return suma;
    }

    public void setSuma(Integer suma) {
        this.suma = suma;
    }

    public List<Elev> getElevi() {return elevi;}
    public void setElevi(List<Elev> elevi) {this.elevi = elevi;}
}
