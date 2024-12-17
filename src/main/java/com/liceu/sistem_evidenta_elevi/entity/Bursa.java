package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

@Entity
@Table(name="burse")
public class Bursa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idBursa;

    private String tip;
    private Integer suma;

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
}
