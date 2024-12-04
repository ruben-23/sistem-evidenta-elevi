package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PermisiuneTest {

    @Test
    public void crearePermisiuneTest() {
        Permisiune permisiune = new Permisiune();
        permisiune.setIdPermisiune(1);
        permisiune.setNume("test");

        assertNotNull(permisiune);
        assertEquals(1, permisiune.getIdPermisiune());
        assertEquals("test", permisiune.getNume());
    }
}
