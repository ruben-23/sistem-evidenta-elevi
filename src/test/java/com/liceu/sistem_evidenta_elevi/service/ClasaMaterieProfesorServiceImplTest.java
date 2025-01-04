package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.*;
import com.liceu.sistem_evidenta_elevi.repository.ClasaMaterieProfesorRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.ClasaMaterieProfesorServiceImpl;
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
public class ClasaMaterieProfesorServiceImplTest {

    @MockBean
    private ClasaMaterieProfesorRepository repository;

    @MockBean
    private ClasaService clasaService;

    @MockBean
    private ProfesorService profesorService;

    @MockBean
    private MaterieService materieService;

    @Autowired
    private ClasaMaterieProfesorServiceImpl service;

    @Test
    public void testGetClasaMaterieProfesorById() {
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(1, 1, 1);
        ClasaMaterieProfesor clasaMaterieProfesor = new ClasaMaterieProfesor();
        clasaMaterieProfesor.setClasa(new Clasa());
        clasaMaterieProfesor.setProfesor(new Profesor());
        clasaMaterieProfesor.setMaterie(new Materie());

        when(repository.findById(id)).thenReturn(Optional.of(clasaMaterieProfesor));

        ClasaMaterieProfesor result = service.getClasaMaterieProfesorById(id);

        assertNotNull(result);
        verify(repository, times(1)).findById(id);
    }

    @Test
    public void testGetClasaMaterieProfesorById_NotFound() {
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(1, 1, 1);
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.getClasaMaterieProfesorById(id);
        });
    }

    @Test
    public void testAdaugaMaterieSiProfesorLaClasa() {
        Integer idClasa = 1;
        Integer idProfesor = 1;
        Integer idMaterie = 1;

        Clasa clasa = new Clasa();
        clasa.setIdClasa(idClasa);
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(idProfesor);
        Materie materie = new Materie();
        materie.setIdMaterie(idMaterie);

        when(clasaService.getClasaById(idClasa)).thenReturn(clasa);
        when(profesorService.getProfesorById(idProfesor)).thenReturn(profesor);
        when(materieService.getMaterieById(idMaterie)).thenReturn(materie);
        when(repository.save(any(ClasaMaterieProfesor.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClasaMaterieProfesor result = service.adaugaMaterieSiProfesorLaClasa(idClasa, idProfesor, idMaterie);

        assertNotNull(result);
        assertEquals(clasa, result.getClasa());
        assertEquals(profesor, result.getProfesor());
        assertEquals(materie, result.getMaterie());
        verify(repository, times(1)).save(any(ClasaMaterieProfesor.class));
    }

    @Test
    public void testStergereMaterieSiProfesorDinClasa() {
        ClasaMaterieProfesorId id = new ClasaMaterieProfesorId(1, 1, 1);
        ClasaMaterieProfesor clasaMaterieProfesor = new ClasaMaterieProfesor();

        when(repository.findById(id)).thenReturn(Optional.of(clasaMaterieProfesor));

        service.stergereMaterieSiProfesorDinClasa(1, 1, 1);

        verify(repository, times(1)).delete(clasaMaterieProfesor);
    }

    @Test
    public void testGetMateriiDinClasa() {
        Integer idClasa = 1;
        Materie materie1 = new Materie();
        materie1.setIdMaterie(1);
        Materie materie2 = new Materie();
        materie2.setIdMaterie(2);

        when(repository.findMateriiByIdClasa(idClasa)).thenReturn(Arrays.asList(materie1, materie2));

        List<Materie> result = service.getMateriiDinClasa(idClasa);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdMaterie());
        assertEquals(2, result.get(1).getIdMaterie());
    }

    @Test
    public void testGetMateriiPredateDeProfesorInClasa() {
        Integer idProfesor = 1;
        Integer idClasa = 1;
        Materie materie1 = new Materie();
        materie1.setIdMaterie(1);
        Materie materie2 = new Materie();
        materie2.setIdMaterie(2);

        when(repository.findMateriiByProfesorAndClasa(idProfesor, idClasa)).thenReturn(Arrays.asList(materie1, materie2));

        List<Materie> result = service.getMateriiPredateDeProfesorInClasa(idProfesor, idClasa);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdMaterie());
        assertEquals(2, result.get(1).getIdMaterie());
    }

    @Test
    public void testGetClaseProfesor() {
        Integer idProfesor = 1;
        Clasa clasa1 = new Clasa();
        clasa1.setIdClasa(1);
        Clasa clasa2 = new Clasa();
        clasa2.setIdClasa(2);

        when(repository.findClaseByProfesorId(idProfesor)).thenReturn(Arrays.asList(clasa1, clasa2));

        List<Clasa> result = service.getClaseProfesor(idProfesor);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdClasa());
        assertEquals(2, result.get(1).getIdClasa());
    }

    @Test
    public void testGetProfesoriDinClasa() {
        Integer idClasa = 1;
        Profesor profesor1 = new Profesor();
        profesor1.setIdProfesor(1);
        Profesor profesor2 = new Profesor();
        profesor2.setIdProfesor(2);

        when(repository.findProfesoriByClasaId(idClasa)).thenReturn(Arrays.asList(profesor1, profesor2));

        List<Profesor> result = service.getProfesoriDinClasa(idClasa);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getIdProfesor());
        assertEquals(2, result.get(1).getIdProfesor());
    }
}