package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.repository.ElevRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ElevServiceImplementare;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ElevServiceImplementareTest {

    @MockBean
    private ElevRepository elevRepository;

    @Autowired
    private ElevServiceImplementare elevService;

    @Test
    public void testGetAllElevi() {
        // Arrange
        Elev elev1 = new Elev();
        elev1.setIdElev(1);
        elev1.setNume("Ion");
        elev1.setPrenume("Popescu");

        when(elevRepository.findAll()).thenReturn(Arrays.asList(elev1));

        // Act
        List<Elev> result = elevService.getAllElevi();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Ion", result.get(0).getNume());
    }

    @Test
    public void testGetElevById() {
        // Arrange
        Elev elev = new Elev();
        elev.setIdElev(1);
        elev.setNume("Ion");
        elev.setPrenume("Popescu");

        when(elevRepository.findById(1)).thenReturn(Optional.of(elev));

        // Act
        Elev result = elevService.getElevById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Ion", result.getNume());
    }

    @Test
    public void testGetElevById_NotFound() {
        // Arrange
        when(elevRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            elevService.getElevById(1);
        });
    }

    @Test
    public void testActualizareElev() {
        // Arrange
        Elev elev = new Elev();
        elev.setIdElev(1);
        elev.setNume("Ion");
        elev.setPrenume("Popescu");
        elev.setCNP("1234567890123");
        elev.setSex("M");
        elev.setNumarTelefon("0712345678");
        elev.setAdresa("Strada 1");
        elev.setDataNasterii(LocalDate.parse("2000-01-01"));

        when(elevRepository.findById(1)).thenReturn(Optional.of(elev));
        when(elevRepository.save(any(Elev.class))).thenReturn(elev);

        // Act
        Elev updatedElev = elevService.actualizareElev(elev);

        // Assert
        assertEquals("Ion", updatedElev.getNume());
        verify(elevRepository, times(1)).save(elev);
    }

    @Test
    public void testAdaugaElev() {
        // Arrange
        ElevRequestDTO requestDTO = new ElevRequestDTO();
        requestDTO.setNume("Maria");
        requestDTO.setPrenume("Ionescu");
        requestDTO.setCNP("9876543210123");
        requestDTO.setNumarTelefon("0723456789");
        requestDTO.setAdresa("Strada 2");
        requestDTO.setDataNasterii(LocalDate.parse("2001-02-02"));

        Elev elev = new Elev();
        elev.setNume("Maria");
        elev.setPrenume("Ionescu");

        when(elevRepository.save(any(Elev.class))).thenReturn(elev);

        // Act
        Elev savedElev = elevService.adaugaElev(requestDTO);

        // Assert
        assertEquals("Maria", savedElev.getNume());
        verify(elevRepository, times(1)).save(any(Elev.class));
    }
}