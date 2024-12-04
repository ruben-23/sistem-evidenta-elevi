package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RolTest {

    @Test
    public void creareRolTest() {
        Rol rol = new Rol();
        rol.setIdRol(1);
        rol.setNume("test");

        assertNotNull(rol);
        assertEquals(1, rol.getIdRol());
        assertEquals("test", rol.getNume());
    }

}
