package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClasaTest {

    @Test
    public void creareClasaTest() {
        Clasa clasa = new Clasa();
        clasa.setIdClasa(1);
        clasa.setNume("Clasa a IX-a A");

        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        clasa.setSpecializare(specializare);

        Profesor diriginte = new Profesor();
        diriginte.setIdProfesor(1);
        clasa.setDiriginte(diriginte);

        List<Elev> elevi = new ArrayList<>();
        Elev elev = new Elev();
        elev.setIdElev(1);
        elevi.add(elev);
        clasa.setElevi(elevi);

        assertNotNull(clasa);
        assertEquals(1, clasa.getIdClasa());
        assertEquals("Clasa a IX-a A", clasa.getNume());
        assertNotNull(clasa.getSpecializare());
        assertEquals(1, clasa.getSpecializare().getIdSpecializare());
        assertNotNull(clasa.getDiriginte());
        assertEquals(1, clasa.getDiriginte().getIdProfesor());
        assertNotNull(clasa.getElevi());
        assertEquals(1, clasa.getElevi().size());
        assertEquals(1, clasa.getElevi().get(0).getIdElev());
    }
}