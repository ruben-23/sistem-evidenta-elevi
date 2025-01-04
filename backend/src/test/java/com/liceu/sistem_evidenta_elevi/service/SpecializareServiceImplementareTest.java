package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.repository.SpecializareRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.SpecializareServiceImplementare;
import org.junit.jupiter.api.Test;
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
public class SpecializareServiceImplementareTest {

    @MockBean
    private SpecializareRepository specializareRepository;

    @Autowired
    private SpecializareServiceImplementare specializareService;

    @Test
    public void testGetAllSpecializari() {
        Specializare specializare1 = new Specializare();
        specializare1.setIdSpecializare(1);
        specializare1.setNume("Matematica");

        when(specializareRepository.findAll()).thenReturn(Arrays.asList(specializare1));

        List<Specializare> result = specializareService.getAllSpecializari();

        assertEquals(1, result.size());
        assertEquals("Matematica", result.get(0).getNume());
    }

    @Test
    public void testGetSpecializareById() {
        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        specializare.setNume("Matematica");

        when(specializareRepository.findById(1)).thenReturn(Optional.of(specializare));

        Specializare result = specializareService.getSpecializareById(1);

        assertNotNull(result);
        assertEquals("Matematica", result.getNume());
    }

    @Test
    public void testGetSpecializareById_NotFound() {
        when(specializareRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            specializareService.getSpecializareById(1);
        });
    }

    @Test
    public void testActualizareSpecializare() {
        SpecializareDTO specializareDTO = new SpecializareDTO();
        specializareDTO.setIdSpecializare(1);
        specializareDTO.setNume("Informatica");

        Specializare specializareActuala = new Specializare();
        specializareActuala.setIdSpecializare(1);
        specializareActuala.setNume("Matematica");

        when(specializareRepository.findById(1)).thenReturn(Optional.of(specializareActuala));
        when(specializareRepository.save(any(Specializare.class))).thenReturn(specializareActuala);

        Specializare updatedSpecializare = specializareService.actualizareSpecializare(specializareDTO);

        assertEquals("Informatica", updatedSpecializare.getNume());
        verify(specializareRepository, times(1)).save(specializareActuala);
    }

    @Test
    public void testAdaugaSpecializare() {
        SpecializareDTO specializareDTO = new SpecializareDTO();
        specializareDTO.setNume("Biologie");

        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        specializare.setNume("Biologie");

        when(specializareRepository.save(any(Specializare.class))).thenReturn(specializare);

        Specializare savedSpecializare = specializareService.adaugaSpecializare(specializareDTO);

        assertEquals("Biologie", savedSpecializare.getNume());
        verify(specializareRepository, times(1)).save(any(Specializare.class));
    }

    @Test
    public void testStergeSpecializare() {
        Integer idSpecializare = 1;

        specializareService.stergeSpecializare(idSpecializare);

        verify(specializareRepository, times(1)).deleteById(idSpecializare);
    }
}