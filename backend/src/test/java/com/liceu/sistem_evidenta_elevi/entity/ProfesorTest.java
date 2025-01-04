package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfesorTest {

    @Test
    public void creareProfesorTest() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNume("Ion");
        profesor.setPrenume("Popescu");
        profesor.setNumarTelefon("0712345678");
        profesor.setAdresa("Strada Exemplu, Nr. 1");
        profesor.setCNP("1234567890123");


        User user = new User();
        user.setIdUser (1);
        profesor.setUser (user);


        Clasa clasa = new Clasa();
        clasa.setIdClasa(1);
        profesor.setClasa(clasa);


        assertNotNull(profesor);
        assertEquals(1, profesor.getIdProfesor());
        assertEquals("Ion", profesor.getNume());
        assertEquals("Popescu", profesor.getPrenume());
        assertEquals("0712345678", profesor.getNumarTelefon());
        assertEquals("Strada Exemplu, Nr. 1", profesor.getAdresa());
        assertEquals("1234567890123", profesor.getCNP());
        assertNotNull(profesor.getUser ());
        assertEquals(1, profesor.getUser ().getIdUser ());
        assertNotNull(profesor.getClasa());
        assertEquals(1, profesor.getClasa().getIdClasa());
    }
}