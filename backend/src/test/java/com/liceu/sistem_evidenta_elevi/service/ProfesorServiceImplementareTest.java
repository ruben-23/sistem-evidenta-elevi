package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.repository.ProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ProfesorServiceImplementare;
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
public class ProfesorServiceImplementareTest {

    @MockBean
    private ProfesorRepository profesorRepository;

    @MockBean
    private ProfesorMapper profesorMapper;

    @MockBean
    private UserService userService;

    @Autowired
    private ProfesorServiceImplementare profesorService;

    @Test
    public void testGetAllProfesori() {
        Profesor profesor1 = new Profesor();
        profesor1.setIdProfesor(1);
        profesor1.setNume("Ion Popescu");

        when(profesorRepository.findAll()).thenReturn(Arrays.asList(profesor1));

        List<Profesor> result = profesorService.getAllProfesori();

        assertEquals(1, result.size());
        assertEquals("Ion Popescu", result.get(0).getNume());
    }

    @Test
    public void testGetProfesorById() {
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNume("Ion Popescu");

        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));

        Profesor result = profesorService.getProfesorById(1);

        assertNotNull(result);
        assertEquals("Ion Popescu", result.getNume());
    }

    @Test
    public void testGetProfesorById_NotFound() {
        when(profesorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            profesorService.getProfesorById(1);
        });
    }

    @Test
    public void testActualizareProfesor() {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setIdProfesor(1);
        profesorDTO.setNume("Ion Popescu");
        profesorDTO.setIdUser (1);

        Profesor profesorActual = new Profesor();
        profesorActual.setIdProfesor(1);
        profesorActual.setNume("Ion Popescu");

        User user = new User();
        user.setIdUser (1);

        when(profesorRepository.findById(1)).thenReturn(Optional.of(profesorActual));
        when(userService.getUserById(1)).thenReturn(user);
        when(profesorMapper.toEntity(profesorDTO, user)).thenReturn(profesorActual);
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesorActual);

        Profesor updatedProfesor = profesorService.actualizareProfesor(profesorDTO);

        assertEquals("Ion Popescu", updatedProfesor.getNume());
        verify(profesorRepository, times(1)).save(profesorActual);
    }

    @Test
    public void testAdaugaProfesor() {
        ProfesorDTO profesorDTO = new ProfesorDTO();
        profesorDTO.setNume("Maria Ionescu");
        User user = new User();
        user.setIdUser (1);

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setNume("Maria Ionescu");

        when(profesorMapper.toEntity(profesorDTO, user)).thenReturn(profesor);
        when(profesorRepository.save(any(Profesor.class))).thenReturn(profesor);

        Profesor savedProfesor = profesorService.adaugaProfesor(profesorDTO, user);

        assertEquals("Maria Ionescu", savedProfesor.getNume());
        verify(profesorRepository, times(1)).save(any(Profesor.class));
    }

    @Test
    public void testStergeProfesor() {
        Integer idProfesor = 1;

        profesorService.stergeProfesor(idProfesor);

        verify(profesorRepository, times(1)).deleteById(idProfesor);
    }
}