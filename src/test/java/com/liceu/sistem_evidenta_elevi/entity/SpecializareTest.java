package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpecializareTest {

    @Test
    public void creareSpecializareTest() {
        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        specializare.setNume("Matematica");


        List<Clasa> clase  = new ArrayList<>();
        Clasa clasa1 = new Clasa();
        clasa1.setIdClasa(1);
        clase.add(clasa1);
        specializare.setClase(clase);


        assertNotNull(specializare);
        assertEquals(1, specializare.getIdSpecializare());
        assertEquals("Matematica", specializare.getNume());
        assertNotNull(specializare.getClase());
        assertEquals(1, specializare.getClase().size());
        assertEquals(1, specializare.getClase().iterator().next().getIdClasa());
    }
}