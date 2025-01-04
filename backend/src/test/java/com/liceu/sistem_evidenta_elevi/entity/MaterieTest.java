package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaterieTest {

    @Test
    public void creareMaterieTest() {
        Materie materie = new Materie();
        materie.setIdMaterie(1);
        materie.setNume("Matematica");


        List<Nota> note = new ArrayList<>();
        Nota nota1 = new Nota();
        nota1.setIdNota(1);
        note.add(nota1);
        materie.setNote(note);


        List<Absenta> absente = new ArrayList<>();
        Absenta absenta1 = new Absenta();
        absenta1.setIdAbsenta(1);
        absente.add(absenta1);
        materie.setAbsente(absente);


        assertNotNull(materie);
        assertEquals(1, materie.getIdMaterie());
        assertEquals("Matematica", materie.getNume());
        assertNotNull(materie.getNote());
        assertEquals(1, materie.getNote().size());
        assertEquals(1, materie.getNote().get(0).getIdNota());
        assertNotNull(materie.getAbsente());
        assertEquals(1, materie.getAbsente().size());
        assertEquals(1, materie.getAbsente().get(0).getIdAbsenta());
    }
}