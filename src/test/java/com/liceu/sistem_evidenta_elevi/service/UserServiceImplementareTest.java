package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.ProfesorMapper;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.UserServiceImplementare;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.service.SecretaraService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplementareTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProfesorService profesorService;

    @MockBean
    private SecretaraService secretaraService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImplementare userService;

    @Test
    public void testGetAllUseri() {
        User user1 = new User();
        user1.setIdUser (1);
        user1.setUsername("ion.popescu");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1));

        List<User> result = userService.getAllUseri();

        assertEquals(1, result.size());
        assertEquals("ion.popescu", result.get(0).getUsername());
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setIdUser (1);
        user.setUsername("ion.popescu");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1);

        assertNotNull(result);
        assertEquals("ion.popescu", result.getUsername());
    }

    @Test
    public void testGetUserById_NotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(1);
        });
    }

    @Test
    public void testActualizeazaUser () {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setIdUser (1);
        userRequestDTO.setUsername("ion.actualizat");
        userRequestDTO.setParola("parolaNoua");
        userRequestDTO.setEmail("ion.nou@exemplu.com");
        userRequestDTO.setRol("ROLE_PROFESOR");

        User userActual = new User();
        userActual.setIdUser (1);
        userActual.setUsername("ion.popescu");
        userActual.setParola("parolaVeche");
        userActual.setEmail("ion.popescu@exemplu.com");

        when(userRepository.findById(1)).thenReturn(Optional.of(userActual));
        when(passwordEncoder.encode(userRequestDTO.getParola())).thenReturn("parolaNoua");
        when(userRepository.save(any(User.class))).thenReturn(userActual);

        User updatedUser  = userService.actualizeazaUser (userRequestDTO);

        assertEquals("ion.actualizat", updatedUser .getUsername());
        assertEquals("parolaNoua", updatedUser .getParola());
        assertEquals("ion.nou@exemplu.com", updatedUser .getEmail());
        verify(userRepository, times(1)).save(userActual);
    }

    @Test
    public void testAdaugaUser () {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("maria.ionescu");
        userRequestDTO.setParola("parola");
        userRequestDTO.setEmail("maria@exemplu.com");
        userRequestDTO.setRol("ROLE_SECRETARA");

        User user = new User();
        user.setIdUser (1);
        user.setUsername("maria.ionescu");
        user.setParola("parola");
        user.setEmail("maria@exemplu.com");

        when(passwordEncoder.encode(userRequestDTO.getParola())).thenReturn("parola");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(secretaraService.adaugaSecretara(any(), any())).thenReturn(new Secretara());

        User savedUser  = userService.adaugaUser (userRequestDTO);

        assertEquals("maria.ionescu", savedUser .getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testStergeUser () {
        Integer idUser  = 1;

        userService.stergeUser (idUser );

        verify(userRepository, times(1)).deleteById(idUser );
    }
}