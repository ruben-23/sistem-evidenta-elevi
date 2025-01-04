package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElevTest {

    @Test
    public void creareElevTest() {
        Elev elev = new Elev();
        elev.setIdElev(1);
        elev.setNume("Ion");
        elev.setPrenume("Popescu");
        elev.setCNP("1234567890123");
        elev.setSex("M");
        elev.setNumarTelefon("0712345678");
        elev.setAdresa("Strada Exemplu, Nr. 1");
        elev.setDataNasterii(LocalDate.parse("2005-05-15"));


        Clasa clasa = new Clasa();
        clasa.setIdClasa(1);
        elev.setClasa(clasa);


        List<Nota> note = new ArrayList<>();
        Nota nota1 = new Nota();
        nota1.setIdNota(1);
        note.add(nota1);
        elev.setNote(note);


        List<Absenta> absente = new ArrayList<>();
        Absenta absenta1 = new Absenta();
        absenta1.setIdAbsenta(1);
        absente.add(absenta1);
        elev.setAbsente(absente);


        List<Bursa> burse = new ArrayList<>();
        Bursa bursa1 = new Bursa();
        bursa1.setIdBursa(1);
        burse.add(bursa1);
        elev.setBurse(burse);


        assertNotNull(elev);
        assertEquals(1, elev.getIdElev());
        assertEquals("Ion", elev.getNume());
        assertEquals("Popescu", elev.getPrenume());
        assertEquals("1234567890123", elev.getCNP());
        assertEquals("M", elev.getSex());
        assertEquals("0712345678", elev.getNumarTelefon());
        assertEquals("Strada Exemplu, Nr. 1", elev.getAdresa());
        assertEquals(LocalDate.parse("2005-05-15"), elev.getDataNasterii());
        assertNotNull(elev.getClasa());
        assertEquals(1, elev.getClasa().getIdClasa());
        assertNotNull(elev.getNote());
        assertEquals(1, elev.getNote().size());
        assertEquals(1, elev.getNote().get(0).getIdNota());
        assertNotNull(elev.getAbsente());
        assertEquals(1, elev.getAbsente().size());
        assertEquals(1, elev.getAbsente().get(0).getIdAbsenta());
        assertNotNull(elev.getBurse());
        assertEquals(1, elev.getBurse().size());
        assertEquals(1, elev.getBurse().get(0).getIdBursa());
    }
}