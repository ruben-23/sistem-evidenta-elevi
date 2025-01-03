package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.SecretaraMapper;
import com.liceu.sistem_evidenta_elevi.repository.SecretaraRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.SecretaraServiceImplementare;
import com.liceu.sistem_evidenta_elevi.service.UserService;
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
public class SecretaraServiceImplementareTest {

    @MockBean
    private SecretaraRepository secretaraRepository;

    @MockBean
    private SecretaraMapper secretaraMapper;

    @MockBean
    private UserService userService;

    @Autowired
    private SecretaraServiceImplementare secretaraService;

    @Test
    public void testGetAllSecretare() {
        Secretara secretara1 = new Secretara();
        secretara1.setIdSecretara(1);
        secretara1.setNume("Maria Ionescu");

        when(secretaraRepository.findAll()).thenReturn(Arrays.asList(secretara1));

        List<Secretara> result = secretaraService.getAllSecretare();

        assertEquals(1, result.size());
        assertEquals("Maria Ionescu", result.get(0).getNume());
    }

    @Test
    public void testGetSecretaraById() {
        Secretara secretara = new Secretara();
        secretara.setIdSecretara(1);
        secretara.setNume("Maria Ionescu");

        when(secretaraRepository.findById(1)).thenReturn(Optional.of(secretara));

        Secretara result = secretaraService.getSecretaraById(1);

        assertNotNull(result);
        assertEquals("Maria Ionescu", result.getNume());
    }

    @Test
    public void testGetSecretaraById_NotFound() {
        when(secretaraRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            secretaraService.getSecretaraById(1);
        });
    }

    @Test
    public void testActualizareSecretara() {
        SecretaraDTO secretaraDTO = new SecretaraDTO();
        secretaraDTO.setIdSecretara(1);
        secretaraDTO.setNume("Maria Ionescu");
        secretaraDTO.setIdUser (1);

        Secretara secretaraActuala = new Secretara();
        secretaraActuala.setIdSecretara(1);
        secretaraActuala.setNume("Maria Ionescu");

        User user = new User();
        user.setIdUser (1);

        when(secretaraRepository.findById(1)).thenReturn(Optional.of(secretaraActuala));
        when(userService.getUserById(1)).thenReturn(user);
        when(secretaraMapper.toEntity(secretaraDTO, user)).thenReturn(secretaraActuala);
        when(secretaraRepository.save(any(Secretara.class))).thenReturn(secretaraActuala);

        Secretara updatedSecretara = secretaraService.actualizareSecretara(secretaraDTO);

        assertEquals("Maria Ionescu", updatedSecretara.getNume());
        verify(secretaraRepository, times(1)).save(secretaraActuala);
    }

    @Test
    public void testAdaugaSecretara() {
        SecretaraDTO secretaraDTO = new SecretaraDTO();
        secretaraDTO.setNume("Ana Popescu");
        User user = new User();
        user.setIdUser (1);

        Secretara secretara = new Secretara();
        secretara.setIdSecretara(1);
        secretara.setNume("Ana Popescu");

        when(secretaraMapper.toEntity(secretaraDTO, user)).thenReturn(secretara);
        when(secretaraRepository.save(any(Secretara.class))).thenReturn(secretara);

        Secretara savedSecretara = secretaraService.adaugaSecretara(secretaraDTO, user);

        assertEquals("Ana Popescu", savedSecretara.getNume());
        verify(secretaraRepository, times(1)).save(any(Secretara.class));
    }

    @Test
    public void testStergeSecretara() {
        Integer idSecretara = 1;

        secretaraService.stergeSecretara(idSecretara);

        verify(secretaraRepository, times(1)).deleteById(idSecretara);
    }
}