package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.BursaDTO;
import com.liceu.sistem_evidenta_elevi.entity.Bursa;
import com.liceu.sistem_evidenta_elevi.mapper.BursaMapper;
import com.liceu.sistem_evidenta_elevi.repository.BursaRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.BursaServiceImplementare;
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
public class BursaServiceImplementareTest {

    @MockBean
    private BursaRepository bursaRepository;

    @MockBean
    private BursaMapper bursaMapper;

    @Autowired
    private BursaServiceImplementare bursaService;

    @Test
    public void testGetAllBurse() {
        Bursa bursa1 = new Bursa();
        bursa1.setIdBursa(1);
        bursa1.setTip("Bursa de merit");
        bursa1.setSuma(1000);

        when(bursaRepository.findAll()).thenReturn(Arrays.asList(bursa1));

        List<Bursa> result = bursaService.getAllBurse();

        assertEquals(1, result.size());
        assertEquals("Bursa de merit", result.get(0).getTip());
    }

    @Test
    public void testGetBursaById() {
        Bursa bursa = new Bursa();
        bursa.setIdBursa(1);
        bursa.setTip("Bursa de merit");
        bursa.setSuma(1000);

        when(bursaRepository.findById(1)).thenReturn(Optional.of(bursa));

        Bursa result = bursaService.getBursaById(1);

        assertNotNull(result);
        assertEquals("Bursa de merit", result.getTip());
    }

    @Test
    public void testGetBursaById_NotFound() {
        when(bursaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            bursaService.getBursaById(1);
        });
    }

    @Test
    public void testActualizareBursa() {
        BursaDTO bursaDTO = new BursaDTO();
        bursaDTO.setIdBursa(1);
        bursaDTO.setTip("Bursa de studiu");
        bursaDTO.setSuma(1500);

        Bursa bursaActuala = new Bursa();
        bursaActuala.setIdBursa(1);
        bursaActuala.setTip("Bursa de studiu");
        bursaActuala.setSuma(1500);

        when(bursaRepository.findById(1)).thenReturn(Optional.of(bursaActuala));
        when(bursaMapper.toEntity(bursaDTO)).thenReturn(bursaActuala);
        when(bursaRepository.save(bursaActuala)).thenReturn(bursaActuala);

        Bursa updatedBursa = bursaService.actualizareBursa(bursaDTO);

        assertEquals("Bursa de studiu", updatedBursa.getTip());
        assertEquals(1500, updatedBursa.getSuma());
        verify(bursaRepository, times(1)).save(bursaActuala);
    }

    @Test
    public void testAdaugaBursa() {
        BursaDTO bursaDTO = new BursaDTO();
        bursaDTO.setTip("Bursa de merit");
        bursaDTO.setSuma(1000);

        Bursa bursa = new Bursa();
        bursa.setIdBursa(1);
        bursa.setTip("Bursa de merit");
        bursa.setSuma(1000);

        when(bursaMapper.toEntity(bursaDTO)).thenReturn(bursa);
        when(bursaRepository.save(any(Bursa.class))).thenReturn(bursa);

        Bursa savedBursa = bursaService.adaugaBursa(bursaDTO);

        assertEquals("Bursa de merit", savedBursa.getTip());
        assertEquals(1000, savedBursa.getSuma());
        verify(bursaRepository, times(1)).save(any(Bursa.class));
    }

    @Test
    public void testStergeBursa() {
        Integer idBursa = 1;

        bursaService.stergeBursa(idBursa);

        verify(bursaRepository, times(1)).deleteById(idBursa);
    }
}