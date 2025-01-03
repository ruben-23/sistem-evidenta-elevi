package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ClasaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Clasa;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.mapper.ClasaMapper;
import com.liceu.sistem_evidenta_elevi.repository.ClasaRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ClasaServiceImplementare;
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
public class ClasaServiceImplementareTest {

    @MockBean
    private ClasaRepository clasaRepository;

    @MockBean
    private ClasaMapper clasaMapper;

    @Autowired
    private ClasaServiceImplementare clasaService;

    @Test
    public void testGetAllClase() {
        Clasa clasa1 = new Clasa();
        clasa1.setIdClasa(1);
        clasa1.setNume("Clasa a IX-a A");

        when(clasaRepository.findAll()).thenReturn(Arrays.asList(clasa1));

        List<Clasa> result = clasaService.getAllClase();

        assertEquals(1, result.size());
        assertEquals("Clasa a IX-a A", result.get(0).getNume());
    }

    @Test
    public void testGetClasaById() {
        Clasa clasa = new Clasa();
        clasa.setIdClasa(1);
        clasa.setNume("Clasa a IX-a A");

        when(clasaRepository.findById(1)).thenReturn(Optional.of(clasa));

        Clasa result = clasaService.getClasaById(1);

        assertNotNull(result);
        assertEquals("Clasa a IX-a A", result.getNume());
    }

    @Test
    public void testGetClasaById_NotFound() {
        when(clasaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            clasaService.getClasaById(1);
        });
    }

    @Test
    public void testActualizareClasa() {
        ClasaDTO clasaDTO = new ClasaDTO();
        clasaDTO.setIdClasa(1);
        clasaDTO.setNume("Clasa a X-a A");

        Clasa clasaActuala = new Clasa();
        clasaActuala.setIdClasa(1);
        clasaActuala.setNume("Clasa a X-a A");

        when(clasaRepository.findById(1)).thenReturn(Optional.of(clasaActuala));
        when(clasaMapper.toEntity(clasaDTO)).thenReturn(clasaActuala);
        when(clasaRepository.save(any(Clasa.class))).thenReturn(clasaActuala);

        Clasa updatedClasa = clasaService.actualizareClasa(clasaDTO);

        assertEquals("Clasa a X-a A", updatedClasa.getNume());
        verify(clasaRepository, times(1)).save(clasaActuala);
    }

    @Test
    public void testAdaugaClasa() {
        ClasaDTO clasaDTO = new ClasaDTO();
        clasaDTO.setNume("Clasa a IX-a B");

        Clasa clasa = new Clasa();
        clasa.setIdClasa(1);
        clasa.setNume("Clasa a IX-a B");

        when(clasaMapper.toEntity(clasaDTO)).thenReturn(clasa);
        when(clasaRepository.save(any(Clasa.class))).thenReturn(clasa);

        Clasa savedClasa = clasaService.adaugaClasa(clasaDTO);

        assertEquals("Clasa a IX-a B", savedClasa.getNume());
        verify(clasaRepository, times(1)).save(any(Clasa.class));
    }

    @Test
    public void testStergeClasa() {
        Integer idClasa = 1;

        Clasa clasa = new Clasa();
        clasa.setIdClasa(idClasa);
        when(clasaRepository.findById(idClasa)).thenReturn(Optional.of(clasa));

        clasaService.stergeClasa(idClasa);

        verify(clasaRepository, times(1)).delete(clasa);
    }

    @Test
    public void testGetEleviByClasa() {
        Integer clasaId = 1;
        Clasa clasa = new Clasa();
        clasa.setIdClasa(clasaId);
        Elev elev1 = new Elev();
        elev1.setIdElev(1);
        Elev elev2 = new Elev();
        elev2.setIdElev(2);
        clasa.setElevi(Arrays.asList(elev1, elev2));

        when(clasaRepository.findById(clasaId)).thenReturn(Optional.of(clasa));

        List<Elev> result = clasaService.getEleviByClasa(clasaId);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdElev());
        assertEquals(2, result.get(1).getIdElev());
    }
}