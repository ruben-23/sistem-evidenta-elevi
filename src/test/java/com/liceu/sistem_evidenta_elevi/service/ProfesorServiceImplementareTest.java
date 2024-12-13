package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ProfesorServiceImplementare;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
public class ProfesorServiceImplementareTest {

    @MockBean
    private ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorServiceImplementare profesorService;

    @Test
    public void testGetAllProfesori() {
        // Arrange
        Profesor profesor1 = new Profesor();
        profesor1.setIdProfesor(1);
        profesor1.setNume("Ion");
        profesor1.setPrenume("Popescu");

        when(profesorRepository.findAll()).thenReturn(Arrays.asList(profesor1));

        // Act
        List<Profesor> result = profesorService.getAllProfesori();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Ion", result.get(0).getNume());
    }

    @Test
    public void testGetProfesorById() {
        // Arrange
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNume("Ion");
        profesor.setPrenume("Popescu");

        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));

        // Act
        Profesor result = profesorService.getProfesorById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Ion", result.getNume());
    }

    @Test
    public void testGetProfesorById_NotFound() {
        // Arrange
        when(profesorRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            profesorService.getProfesorById(1);
        });
        assertEquals("Profesorul nu a fost gasit", exception.getMessage());
    }

    @Test
    public void testActualizareProfesor() {
        // Arrange
        ProfesorRequestDTO requestDTO = new ProfesorRequestDTO();
        requestDTO.setIdProfesor(1);
        requestDTO.setNume("Ion");
        requestDTO.setPrenume("Popescu");
        requestDTO.setAdresa("Strada 1");
        requestDTO.setCNP("1234567890123");
        requestDTO.setNumarTelefon("0712345678");

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNume("Ion");
        profesor.setPrenume("Popescu");

        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);

        // Act
        Profesor updatedProfesor = profesorService.actualizareProfesor(requestDTO);

        // Assert
        assertEquals("Strada 1", updatedProfesor.getAdresa());
        verify(profesorRepository, times(1)).save(profesor);
    }

    @Test
    public void testAdaugaProfesor() {
        // Arrange
        ProfesorRequestDTO requestDTO = new ProfesorRequestDTO();
        requestDTO.setNume("Maria");
        requestDTO.setPrenume("Ionescu");
        requestDTO.setAdresa("Strada 2");
        requestDTO.setCNP("9876543210123");
        requestDTO.setNumarTelefon("0723456789");

        Profesor profesor = new Profesor();
        profesor.setNume("Maria");
        profesor.setPrenume("Ionescu");

        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);

        // Act
        Profesor savedProfesor = profesorService.adaugaProfesor(requestDTO);

        // Assert
        assertEquals("Maria", savedProfesor.getNume());
        verify(profesorRepository, times(1)).save(any(Profesor.class));
    }
}