package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.MaterieDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.mapper.MaterieMapper;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.MaterieServiceImplementare;
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
public class MaterieServiceImplementareTest {

    @MockBean
    private MaterieRepository materieRepository;

    @MockBean
    private MaterieMapper materieMapper;

    @Autowired
    private MaterieServiceImplementare materieService;

    @Test
    public void testGetAllMaterii() {
        Materie materie1 = new Materie();
        materie1.setIdMaterie(1);
        materie1.setNume("Matematica");

        when(materieRepository.findAll()).thenReturn(Arrays.asList(materie1));

        List<Materie> result = materieService.getAllMaterii();

        assertEquals(1, result.size());
        assertEquals("Matematica", result.get(0).getNume());
    }

    @Test
    public void testGetMaterieById() {
        Materie materie = new Materie();
        materie.setIdMaterie(1);
        materie.setNume("Matematica");

        when(materieRepository.findById(1)).thenReturn(Optional.of(materie));

        Materie result = materieService.getMaterieById(1);

        assertNotNull(result);
        assertEquals("Matematica", result.getNume());
    }

    @Test
    public void testGetMaterieById_NotFound() {
        when(materieRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            materieService.getMaterieById(1);
        });
    }

    @Test
    public void testActualizareMaterie() {
        MaterieDTO materieDTO = new MaterieDTO();
        materieDTO.setIdMaterie(1);
        materieDTO.setNume("Informatica");

        Materie materieActuala = new Materie();
        materieActuala.setIdMaterie(1);
        materieActuala.setNume("Informatica");

        when(materieRepository.findById(1)).thenReturn(Optional.of(materieActuala));
        when(materieMapper.toEntity(materieDTO)).thenReturn(materieActuala);
        when(materieRepository.save(any(Materie.class))).thenReturn(materieActuala);

        Materie updatedMaterie = materieService.actualizareMaterie(materieDTO);

        assertEquals("Informatica", updatedMaterie.getNume());
        verify(materieRepository, times(1)).save(materieActuala);
    }

    @Test
    public void testAdaugaMaterie() {
        MaterieDTO materieDTO = new MaterieDTO();
        materieDTO.setNume("Biologie");

        Materie materie = new Materie();
        materie.setIdMaterie(1);
        materie.setNume("Biologie");

        when(materieMapper.toEntity(materieDTO)).thenReturn(materie);
        when(materieRepository.save(any(Materie.class))).thenReturn(materie);

        Materie savedMaterie = materieService.adaugaMaterie(materieDTO);

        assertEquals("Biologie", savedMaterie.getNume());
        verify(materieRepository, times(1)).save(any(Materie.class));
    }

    @Test
    public void testStergeMaterie() {
        Integer idMaterie = 1;

        materieService.stergeMaterie(idMaterie);

        verify(materieRepository, times(1)).deleteById(idMaterie);
    }
}