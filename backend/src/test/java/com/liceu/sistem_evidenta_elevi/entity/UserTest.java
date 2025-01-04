package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void creareUserTest() {
        User user = new User();
        user.setIdUser (1);
        user.setUsername("ion.popescu");
        user.setParola("parola123");
        user.setEmail("ion.popescu@exemplu.com");
        user.setRol(Rol.ROLE_PROFESOR);


        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        user.setProfesor(profesor);


        Secretara secretara = new Secretara();
        secretara.setIdSecretara(1);
        user.setSecretara(secretara);


        assertNotNull(user);
        assertEquals(1, user.getIdUser ());
        assertEquals("ion.popescu", user.getUsername());
        assertEquals("parola123", user.getParola());
        assertEquals("ion.popescu@exemplu.com", user.getEmail());
        assertEquals(Rol.ROLE_PROFESOR, user.getRol());
        assertNotNull(user.getProfesor());
        assertEquals(1, user.getProfesor().getIdProfesor());
        assertNotNull(user.getSecretara());
        assertEquals(1, user.getSecretara().getIdSecretara());
    }
}