package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevDTO;
import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.mapper.ElevMapper;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ElevServiceImplementare;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ElevServiceImplementareTest {

    @MockBean
    private ElevRepository elevRepository;

    @MockBean
    private ElevMapper elevMapper;

    @MockBean
    private BursaService bursaService;

    @Autowired
    private ElevServiceImplementare elevService;

    @Test
    public void testGetAllElevi() {
        Elev elev1 = new Elev();
        elev1.setIdElev(1);
        elev1.setNume("Ion");

        when(elevRepository.findAll()).thenReturn(Arrays.asList(elev1));

        List<Elev> result = elevService.getAllElevi();

        assertEquals(1, result.size());
        assertEquals("Ion", result.get(0).getNume());
    }

    @Test
    public void testGetElevById() {
        Elev elev = new Elev();
        elev.setIdElev(1);
        elev.setNume("Ion");

        when(elevRepository.findById(1)).thenReturn(Optional.of(elev));

        Elev result = elevService.getElevById(1);

        assertNotNull(result);
        assertEquals("Ion", result.getNume());
    }

    @Test
    public void testGetElevById_NotFound() {
        when(elevRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            elevService.getElevById(1);
        });
    }

    @Test
    public void testActualizareElev() {
        ElevDTO elevDTO = new ElevDTO();
        elevDTO.setIdElev(1);
        elevDTO.setNume("Ion");

        Elev elevActual = new Elev();
        elevActual.setIdElev(1);
        elevActual.setNume("Ion");

        when(elevRepository.findById(1)).thenReturn(Optional.of(elevActual));
        when(elevMapper.toEntity(elevDTO)).thenReturn(elevActual);
        when(elevRepository.save(any(Elev.class))).thenReturn(elevActual);

        Elev updatedElev = elevService.actualizareElev(elevDTO);

        assertEquals("Ion", updatedElev.getNume());
        verify(elevRepository, times(1)).save(elevActual);
    }

    @Test
    public void testAdaugaElev() {
        ElevDTO elevDTO = new ElevDTO();
        elevDTO.setNume("Maria");

        Elev elev = new Elev();
        elev.setIdElev(1);
        elev.setNume("Maria");

        when(elevMapper.toEntity(elevDTO)).thenReturn(elev);
        when(elevRepository.save(any(Elev.class))).thenReturn(elev);

        Elev savedElev = elevService.adaugaElev(elevDTO);

        assertEquals("Maria", savedElev.getNume());
        verify(elevRepository, times(1)).save(any(Elev.class));
    }

    @Test
    public void testStergeElev() {
        Integer idElev = 1;

        elevService.stergeElev(idElev);

        verify(elevRepository, times(1)).deleteByIdElev(idElev);
    }

    @Test
    public void testGetNoteElev() {
        Integer idElev = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Nota nota1 = new Nota();
        nota1 .setIdNota(1);
        Nota nota2 = new Nota();
        nota2.setIdNota(2);
        elev.setNote(Arrays.asList(nota1, nota2));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));

        List<Nota> result = elevService.getNoteElev(idElev);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdNota());
        assertEquals(2, result.get(1).getIdNota());
    }

    @Test
    public void testGetAbsenteElev() {
        Integer idElev = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Absenta absenta1 = new Absenta();
        absenta1.setIdAbsenta(1);
        Absenta absenta2 = new Absenta();
        absenta2.setIdAbsenta(2);
        elev.setAbsente(Arrays.asList(absenta1, absenta2));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));

        List<Absenta> result = elevService.getAbsenteElev(idElev);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdAbsenta());
        assertEquals(2, result.get(1).getIdAbsenta());
    }

    @Test
    public void testGetNoteElevMaterie() {
        Integer idElev = 1;
        Integer idMaterie = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Nota nota1 = new Nota();
        nota1.setIdNota(1);
        nota1.setMaterie(new Materie());
        Nota nota2 = new Nota();
        nota2.setIdNota(2);
        nota2.setMaterie(new Materie());
        elev.setNote(Arrays.asList(nota1, nota2));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));

        List<Nota> result = elevService.getNoteElevMaterie(idElev, idMaterie);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdNota());
    }

    @Test
    public void testGetAbsenteElevMaterie() {
        Integer idElev = 1;
        Integer idMaterie = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Absenta absenta1 = new Absenta();
        absenta1.setIdAbsenta(1);
        absenta1.setMaterie(new Materie());
        Absenta absenta2 = new Absenta();
        absenta2.setIdAbsenta(2);
        absenta2.setMaterie(new Materie());
        elev.setAbsente(Arrays.asList(absenta1, absenta2));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));

        List<Absenta> result = elevService.getAbsenteElevMaterie(idElev, idMaterie);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getIdAbsenta());
    }

    @Test
    public void testGetBurseElev() {
        Integer idElev = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Bursa bursa1 = new Bursa();
        bursa1.setIdBursa(1);
        Bursa bursa2 = new Bursa();
        bursa2.setIdBursa(2);
        elev.setBurse(Arrays.asList(bursa1, bursa2));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));

        List<Bursa> result = elevService.getBurseElev(idElev);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdBursa());
        assertEquals(2, result.get(1).getIdBursa());
    }

    @Test
    public void testAdaugaBursaLaElev() {
        Integer idElev = 1;
        Integer idBursa = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Bursa bursa = new Bursa();
        bursa.setIdBursa(idBursa);

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));
        when(bursaService.getBursaById(idBursa)).thenReturn(bursa);
        when(elevRepository.save(any(Elev.class))).thenReturn(elev);

        Elev updatedElev = elevService.adaugaBursaLaElev(idElev, idBursa);

        assertTrue(updatedElev.getBurse().contains(bursa));
        verify(elevRepository, times(1)).save(elev);
    }

    @Test
    public void testStergeBursaLaElev() {
        Integer idElev = 1;
        Integer idBursa = 1;
        Elev elev = new Elev();
        elev.setIdElev(idElev);
        Bursa bursa = new Bursa();
        bursa.setIdBursa(idBursa);
        elev.setBurse(Arrays.asList(bursa));

        when(elevRepository.findById(idElev)).thenReturn(Optional.of(elev));
        when(bursaService.getBursaById(idBursa)).thenReturn(bursa);
        when(elevRepository.save(any(Elev.class))).thenReturn(elev);

        elevService.stergeBursaLaElev(idElev, idBursa);

        assertFalse(elev.getBurse().contains(bursa));
        verify(elevRepository, times(1)).save(elev);
    }
}