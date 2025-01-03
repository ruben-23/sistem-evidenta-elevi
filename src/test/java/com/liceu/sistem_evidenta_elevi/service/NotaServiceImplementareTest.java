package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.NotaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Nota;
import com.liceu.sistem_evidenta_elevi.mapper.NotaMapper;
import com.liceu.sistem_evidenta_elevi.repository.NotaRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.NotaServiceImplementare;
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
public class NotaServiceImplementareTest {

    @MockBean
    private NotaRepository notaRepository;

    @MockBean
    private NotaMapper notaMapper;

    @Autowired
    private NotaServiceImplementare notaService;

    @Test
    public void testGetAllNote() {
        Nota nota1 = new Nota();
        nota1.setIdNota(1);
        nota1.setValoare(10.0);

        when(notaRepository.findAll()).thenReturn(Arrays.asList(nota1));

        List<Nota> result = notaService.getAllNote();

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getValoare());
    }

    @Test
    public void testGetNotaById() {
        Nota nota = new Nota();
        nota.setIdNota(1);
        nota.setValoare(10.0);

        when(notaRepository.findById(1)).thenReturn(Optional.of(nota));

        Nota result = notaService.getNotaById(1);

        assertNotNull(result);
        assertEquals(10, result.getValoare());
    }

    @Test
    public void testGetNotaById_NotFound() {
        when(notaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            notaService.getNotaById(1);
        });
    }

    @Test
    public void testActualizareNota() {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setIdNota(1);
        notaDTO.setValoare(9.0);

        Nota notaActuala = new Nota();
        notaActuala.setIdNota(1);
        notaActuala.setValoare(9.0);

        when(notaRepository.findById(1)).thenReturn(Optional.of(notaActuala));
        when(notaMapper.toEntity(notaDTO)).thenReturn(notaActuala);
        when(notaRepository.save(any(Nota.class))).thenReturn(notaActuala);

        Nota updatedNota = notaService.actualizareNota(notaDTO);

        assertEquals(9, updatedNota.getValoare());
        verify(notaRepository, times(1)).save(notaActuala);
    }

    @Test
    public void testAdaugaNota() {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setValoare(10.0);

        Nota nota = new Nota();
        nota.setIdNota(1);
        nota.setValoare(10.0);

        when(notaMapper.toEntity(notaDTO)).thenReturn(nota);
        when(notaRepository.save(any(Nota.class))).thenReturn(nota);

        Nota savedNota = notaService.adaugaNota(notaDTO);

        assertEquals(10, savedNota.getValoare());
        verify(notaRepository, times(1)).save(any(Nota.class));
    }

    @Test
    public void testStergeNota() {
        Integer idNota = 1;

        notaService.stergeNota(idNota);

        verify(notaRepository, times(1)).deleteById(idNota);
    }
}