package com.liceu.sistem_evidenta_elevi.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity

@Table(name="elevi")
public class Elev {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idElev;

    @Column(nullable = false)
    private String numeElev;

    @Column(nullable = false)
    private String prenumeElev;

    @Column(nullable = false)
    private String CNP;

    @Column(nullable = false)
    private String sexElev;

    @Column(nullable = false)
    private String numarTelefonElev;

    @Column(nullable = false)
    private String adresaElev;

    @Column(nullable = false)
    private Date dataNasteriiElev;

}
