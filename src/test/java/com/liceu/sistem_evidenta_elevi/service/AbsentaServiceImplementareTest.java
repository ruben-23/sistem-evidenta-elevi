package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.AbsentaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Absenta;
import com.liceu.sistem_evidenta_elevi.mapper.AbsentaMapper;
import com.liceu.sistem_evidenta_elevi.repository.AbsentaRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.AbsentaServiceImplementare;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AbsentaServiceImplementareTest {

    @MockBean
    private AbsentaRepository absentaRepository;

    @MockBean
    private AbsentaMapper absentaMapper;

    @Autowired
    private AbsentaServiceImplementare absentaService;

    @Test
    public void testGetAllAbsente() {
        Absenta absenta1 = new Absenta();
        absenta1.setIdAbsenta(1);
        absenta1.setData(LocalDate.parse("2023-10-01"));
        absenta1.setModul("Matematica");

        when(absentaRepository.findAll()).thenReturn(Arrays.asList(absenta1));

        List<Absenta> result = absentaService.getAllAbsente();

        assertEquals(1, result.size());
        assertEquals("Matematica", result.get(0).getModul());
    }

    @Test
    public void testGetAbsentaById() {
        Absenta absenta = new Absenta();
        absenta.setIdAbsenta(1);
        absenta.setData(LocalDate.parse("2023-10-01"));
        absenta.setModul("Matematica");

        when(absentaRepository.findById(1)).thenReturn(Optional.of(absenta));

        Absenta result = absentaService.getAbsentaById(1);

        assertNotNull(result);
        assertEquals("Matematica", result.getModul());
    }

    @Test
    public void testGetAbsentaById_NotFound() {
        when(absentaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            absentaService.getAbsentaById(1);
        });
    }

    @Test
    public void testActualizareAbsenta() {
        AbsentaDTO absentaDTO = new AbsentaDTO();
        absentaDTO.setIdAbsenta(1);
        absentaDTO.setData(LocalDate.parse("2023-10-01"));
        absentaDTO.setModul("Informatica");

        Absenta absentaActuala = new Absenta();
        absentaActuala.setIdAbsenta(1);
        absentaActuala.setData(LocalDate.parse("2023-10-01"));
        absentaActuala.setModul("Matematica");

        when(absentaRepository.findById(1)).thenReturn(Optional.of(absentaActuala));
        when(absentaMapper.toEntity(absentaDTO)).thenReturn(absentaActuala);
        when(absentaRepository.save(any(Absenta.class))).thenReturn(absentaActuala);

        Absenta updatedAbsenta = absentaService.actualizareAbsenta(absentaDTO);

        assertEquals("Matematica", updatedAbsenta.getModul());
        verify(absentaRepository, times(1)).save(absentaActuala);
    }

    @Test
    public void testAdaugaAbsenta() {
        AbsentaDTO absentaDTO = new AbsentaDTO();
        absentaDTO.setData(LocalDate.parse("2023-10-01"));
        absentaDTO.setModul("Matematica");

        Absenta absenta = new Absenta();
        absenta.setIdAbsenta(1);
        absenta.setData(LocalDate.parse("2023-10-01"));
        absenta.setModul("Matematica");

        when(absentaMapper.toEntity(absentaDTO)).thenReturn(absenta);
        when(absentaRepository.save(any(Absenta.class))).thenReturn(absenta);

        Absenta savedAbsenta = absentaService.adaugaAbsenta(absentaDTO);

        assertEquals("Matematica", savedAbsenta.getModul());
        verify(absentaRepository, times(1)).save(any(Absenta.class));
    }

    @Test
    public void testStergeAbsenta() {
        Integer idAbsenta = 1;

        absentaService.stergeAbsenta(idAbsenta);

        verify(absentaRepository, times(1)).deleteById(idAbsenta);
    }
}