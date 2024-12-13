package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.MaterieRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Materie;
import com.liceu.sistem_evidenta_elevi.repository.MaterieRepository;
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
public class MaterieServiceImplementare {

    @MockBean
    private MaterieRepository materieRepository;

    @Autowired
    private com.liceu.sistem_evidenta_elevi.service.implementare.MaterieServiceImplementare materieService;

    @Test
    public void testGetAllMaterii() {
        // Arrange
        Materie materie1 = new Materie();
        materie1.setIdMaterie(1);
        materie1.setNume("Matematica");

        when(materieRepository.findAll()).thenReturn(Arrays.asList(materie1));

        // Act
        List<Materie> result = materieService.getAllMaterii();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Matematica", result.get(0).getNume());
    }

    @Test
    public void testGetMaterieById() {
        // Arrange
        Materie materie = new Materie();
        materie.setIdMaterie(1);
        materie.setNume("Matematica");

        when(materieRepository.findById(1)).thenReturn(Optional.of(materie));

        // Act
        Materie result = materieService.getMaterieById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Matematica", result.getNume());
    }

    @Test
    public void testGetMaterieById_NotFound() {
        // Arrange
        when(materieRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            materieService.getMaterieById(1);
        });
        assertEquals("Materia nu a fost gasita", exception.getMessage());
    }

    @Test
    public void testActualizareMaterie() {
        // Arrange
        MaterieRequestDTO requestDTO = new MaterieRequestDTO();
        requestDTO.setIdMaterie(1);
        requestDTO.setNume("Matematica Avansata");

        Materie materie = new Materie();
        materie.setIdMaterie(1);
        materie.setNume("Matematica");

        when(materieRepository.findById(1)).thenReturn(Optional.of(materie));
        when(materieRepository.save(any(Materie.class))).thenReturn(materie);

        // Act
        Materie updatedMaterie = materieService.actualizareMaterie(requestDTO);

        // Assert
        assertEquals("Matematica Avansata", updatedMaterie.getNume());
        verify(materieRepository, times(1)).save(materie);
    }

    @Test
    public void testAdaugaMaterie() {
        // Arrange
        MaterieRequestDTO requestDTO = new MaterieRequestDTO();
        requestDTO.setNume("Biologie");

        Materie materie = new Materie();
        materie.setNume("Biologie");

        when(materieRepository.save(any(Materie.class))).thenReturn(materie);

        // Act
        Materie savedMaterie = materieService.adaugaMaterie(requestDTO);

        // Assert
        assertEquals("Biologie", savedMaterie.getNume());
        verify(materieRepository, times(1)).save(any(Materie.class));
    }
}