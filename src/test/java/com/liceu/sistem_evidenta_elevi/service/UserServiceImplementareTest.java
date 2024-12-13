package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.Rol;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.implementare.UserServiceImplementare;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceImplementareTest {

    @Autowired
    private UserServiceImplementare userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProfesorService profesorService;

    @Test
    public void testAdaugaUser_ValidInput() {
        // Arrange
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setUsername("testUser ");
        userRequest.setParola("password");
        userRequest.setEmail("test@example.com");
        userRequest.setRol("ROLE_PROFESOR");

        ProfesorRequestDTO profesorRequest = new ProfesorRequestDTO();
        profesorRequest.setIdProfesor(1);
        userRequest.setProfesor(profesorRequest);

        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        profesor.setUser (new User());

        when(profesorService.adaugaProfesor(any(ProfesorRequestDTO.class))).thenReturn(profesor);
        when(profesorService.getProfesorById(1)).thenReturn(profesor);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.adaugaUser (userRequest);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getUsername()).isEqualTo("testUser ");
        assertThat(result.getRol()).isEqualTo(Rol.ROLE_PROFESOR);
        assertThat(result.getProfesor()).isEqualTo(profesor);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testAdaugaUser_InvalidRole() {
        // Arrange
        UserRequestDTO userRequest = new UserRequestDTO();
        userRequest.setRol("ROLE_INVALID");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.adaugaUser (userRequest);
        });
        assertThat(exception.getMessage()).isEqualTo("Rolul nu exista");
    }

    @Test
    public void testAdaugaUser_NullInput() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.adaugaUser (null);
        });
        assertThat(exception.getMessage()).isEqualTo("User nu poate fi null");
    }
}