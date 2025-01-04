package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AbsentaTest {

    @Test
    public void creareAbsentaTest() {
        Absenta absenta = new Absenta();
        absenta.setIdAbsenta(1);
        absenta.setData(LocalDate.parse("2023-10-01"));
        absenta.setModul("Modul 2");

        Elev elev = new Elev();
        elev.setIdElev(1);
        absenta.setElev(elev);

        Materie materie = new Materie();
        materie.setIdMaterie(1);
        absenta.setMaterie(materie);

        assertNotNull(absenta);
        assertEquals(1, absenta.getIdAbsenta());
        assertEquals(LocalDate.parse("2023-10-01"), absenta.getData());
        assertEquals("Modul 2", absenta.getModul());
        assertNotNull(absenta.getElev());
        assertEquals(1, absenta.getElev().getIdElev());
        assertNotNull(absenta.getMaterie());
        assertEquals(1, absenta.getMaterie().getIdMaterie());
    }
}