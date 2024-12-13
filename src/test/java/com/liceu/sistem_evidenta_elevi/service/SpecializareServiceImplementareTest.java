package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SpecializareRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Specializare;
import com.liceu.sistem_evidenta_elevi.repository.SpecializareRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.SpecializareServiceImplementare;
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
public class SpecializareServiceImplementareTest {

    @MockBean
    private SpecializareRepository specializareRepository;

    @Autowired
    private SpecializareServiceImplementare specializareService;

    @Test
    public void testGetAllSpecializari() {
        // Arrange
        Specializare specializare1 = new Specializare();
        specializare1.setIdSpecializare(1);
        specializare1.setNume("Informatica");

        when(specializareRepository.findAll()).thenReturn(Arrays.asList(specializare1));

        // Act
        List<Specializare> result = specializareService.getAllSpecializari();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Informatica", result.get(0).getNume());
    }

    @Test
    public void testGetSpecializareById() {
        // Arrange
        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        specializare.setNume("Informatica");

        when(specializareRepository.findById(1)).thenReturn(Optional.of(specializare));

        // Act
        Specializare result = specializareService.getSpecializareById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Informatica", result.getNume());
    }

    @Test
    public void testGetSpecializareById_NotFound() {
        // Arrange
        when(specializareRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            specializareService.getSpecializareById(1);
        });
        assertEquals("Specializarea nu a fost gasita", exception.getMessage());
    }

    @Test
    public void testActualizareSpecializare() {
        // Arrange
        SpecializareRequestDTO requestDTO = new SpecializareRequestDTO();
        requestDTO.setIdSpecializare(1);
        requestDTO.setNume("Informatica Avansata");

        Specializare specializare = new Specializare();
        specializare.setIdSpecializare(1);
        specializare.setNume("Informatica");

        when(specializareRepository.findById(1)).thenReturn(Optional.of(specializare));
        when(specializareRepository.save(any(Specializare.class))).thenReturn(specializare);

        // Act
        Specializare updatedSpecializare = specializareService.actualizareSpecializare(requestDTO);

        // Assert
        assertEquals("Informatica Avansata", updatedSpecializare.getNume());
        verify(specializareRepository, times(1)).save(specializare);
    }

    @Test
    public void testAdaugaSpecializare() {
        // Arrange
        SpecializareRequestDTO requestDTO = new SpecializareRequestDTO();
        requestDTO.setNume("Matematica");

        Specializare specializare = new Specializare();
        specializare.setNume("Matematica");

        when(specializareRepository.save(any(Specializare.class))).thenReturn(specializare);

        // Act
        Specializare savedSpecializare = specializareService.adaugaSpecializare(requestDTO);

        // Assert
        assertEquals("Matematica", savedSpecializare.getNume());
        verify(specializareRepository, times(1)).save(any(Specializare.class));
    }
}