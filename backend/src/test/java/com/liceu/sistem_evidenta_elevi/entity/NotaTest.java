package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class NotaTest {

    @Test
    public void creareNotaTest() {
        Nota nota = new Nota();
        nota.setIdNota(1);
        nota.setValoare(9.5);
        nota.setData(LocalDate.parse("2023-10-01"));
        nota.setModul("Modul 1");


        Elev elev = new Elev();
        elev.setIdElev(1);
        nota.setElev(elev);


        Materie materie = new Materie();
        materie.setIdMaterie(1);
        nota.setMaterie(materie);


        assertNotNull(nota);
        assertEquals(1, nota.getIdNota());
        assertEquals(9.5, nota.getValoare());
        assertEquals(LocalDate.parse("2023-10-01"), nota.getData());
        assertEquals("Modul 1", nota.getModul());
        assertNotNull(nota.getElev());
        assertEquals(1, nota.getElev().getIdElev());
        assertNotNull(nota.getMaterie());
        assertEquals(1, nota.getMaterie().getIdMaterie());
    }
}