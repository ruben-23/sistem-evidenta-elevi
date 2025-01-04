package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClasaMaterieProfesorIdTest {

    @Test
    public void testEqualsAndHashCode() {
        ClasaMaterieProfesorId id1 = new ClasaMaterieProfesorId(1, 1, 1);
        ClasaMaterieProfesorId id2 = new ClasaMaterieProfesorId(1, 1, 1);
        ClasaMaterieProfesorId id3 = new ClasaMaterieProfesorId(2, 1, 1);
        ClasaMaterieProfesorId id4 = new ClasaMaterieProfesorId(1, 2, 1);
        ClasaMaterieProfesorId id5 = new ClasaMaterieProfesorId(1, 1, 2);


        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());


        assertNotEquals(id1, id3);
        assertNotEquals(id1.hashCode(), id3.hashCode());


        assertNotEquals(id1, id4);
        assertNotEquals(id1.hashCode(), id4.hashCode());


        assertNotEquals(id1, id5);
        assertNotEquals(id1.hashCode(), id5.hashCode());
    }

    @Test
    public void testGettersAndSetters() {
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId();
        id.setIdClasa(1);
        id.setIdProfesor(2);
        id.setIdMaterie(3);

        assertEquals(1, id.getIdClasa());
        assertEquals(2, id.getIdProfesor());
        assertEquals(3, id.getIdMaterie());
    }
}