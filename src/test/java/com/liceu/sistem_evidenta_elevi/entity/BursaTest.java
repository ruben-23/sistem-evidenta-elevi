package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BursaTest {

    @Test
    public void creareBursaTest() {
        Bursa bursa = new Bursa();
        bursa.setIdBursa(1);
        bursa.setTip("Bursa de merit");
        bursa.setSuma(1000);

        List<Elev> elevi = new ArrayList<>();
        Elev elev = new Elev();
        elev.setIdElev(1);
        elevi.add(elev);
        bursa.setElevi(elevi);

        assertNotNull(bursa);
        assertEquals(1, bursa.getIdBursa());
        assertEquals("Bursa de merit", bursa.getTip());
        assertEquals(1000, bursa.getSuma());
        assertNotNull(bursa.getElevi());
        assertEquals(1, bursa.getElevi().size());
        assertEquals(1, bursa.getElevi().get(0).getIdElev());
    }
}