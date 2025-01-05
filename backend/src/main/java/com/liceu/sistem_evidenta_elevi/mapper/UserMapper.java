package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserResponseDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru maparea entitatii User in obiecte DTO de tip UserResponseDTO.
 * Aceasta clasa este responsabila pentru conversia datelor din entitatea User intr-un obiect DTO de raspuns,
 * precum si pentru conversia unei liste de utilizatori intr-o lista de obiecte DTO.
 */
@Component
public class UserMapper {

    private final ProfesorMapper profesorMapper;
    private final SecretaraMapper secretaraMapper;

    /**
     * Constructorul pentru UserMapper.
     *
     * @param profesorMapper Mapper-ul pentru entitatea Profesor.
     * @param secretaraMapper Mapper-ul pentru entitatea Secretara.
     */
    @Autowired
    UserMapper(ProfesorMapper profesorMapper, SecretaraMapper secretaraMapper) {
        this.profesorMapper = profesorMapper;
        this.secretaraMapper = secretaraMapper;
    }

    /**
     * Converteste un obiect User intr-un obiect UserResponseDTO.
     *
     * @param user Entitatea User care trebuie convertita într-un DTO.
     * @return Un obiect UserResponseDTO populat cu datele din entitatea User.
     */
    public UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setIdUser(user.getIdUser());
        userResponse.setUsername(user.getUsername());
        userResponse.setRol(user.getRol().toString());
        userResponse.setEmail(user.getEmail());

        if(user.getProfesor() != null) {
            ProfesorDTO profesor = profesorMapper.toDTO(user.getProfesor());
            userResponse.setProfesor(profesor);
        } else if(user.getSecretara() != null) {
            SecretaraDTO secretara = secretaraMapper.toDTO(user.getSecretara());
            userResponse.setSecretara(secretara);
        }

        return userResponse;
    }

    /**
     * Converteste o lista de obiecte User intr-o lista de obiecte UserResponseDTO.
     *
     * @param useri Lista de entitati User care trebuie convertita intr-o listă de DTO-uri.
     * @return O lista de obiecte UserResponseDTO populata cu datele din lista de entitati User.
     */
    public List<UserResponseDTO> toResponseDTOList(List<User> useri) {
        List<UserResponseDTO> userDTOs = new ArrayList<>();
        for (User user : useri) {
            userDTOs.add(toResponseDTO(user));
        }
        return userDTOs;
    }
}