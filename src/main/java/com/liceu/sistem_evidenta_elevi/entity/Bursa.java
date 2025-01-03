package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="burse")
public class Bursa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idBursa;

    @Column(nullable=false)
    private String tip;

    @Column(nullable=false)
    private Integer suma;

    @ManyToMany(mappedBy = "burse")
    private List<Elev> elevi;

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
