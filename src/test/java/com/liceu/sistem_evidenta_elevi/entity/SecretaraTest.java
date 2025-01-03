package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecretaraTest {

    @Test
    public void creareSecretaraTest() {
        Secretara secretara = new Secretara();
        secretara.setIdSecretara(1);
        secretara.setNume("Maria");
        secretara.setPrenume("Ionescu");
        secretara.setNumarTelefon("0723456789");
        secretara.setAdresa("Strada Exemplu, Nr. 2");
        secretara.setCNP("1234567890123");


        User user = new User();
        user.setIdUser (1);
        secretara.setUser (user);


        assertNotNull(secretara);
        assertEquals(1, secretara.getIdSecretara());
        assertEquals("Maria", secretara.getNume());
        assertEquals("Ionescu", secretara.getPrenume());
        assertEquals("0723456789", secretara.getNumarTelefon());
        assertEquals("Strada Exemplu, Nr. 2", secretara.getAdresa());
        assertEquals("1234567890123", secretara.getCNP());
        assertNotNull(secretara.getUser ());
        assertEquals(1, secretara.getUser ().getIdUser ());
    }
}