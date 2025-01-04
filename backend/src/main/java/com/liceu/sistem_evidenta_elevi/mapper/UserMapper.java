package com.liceu.sistem_evidenta_elevi.mapper;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserResponseDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final ProfesorMapper profesorMapper;
    private final SecretaraMapper secretaraMapper;

    @Autowired
    UserMapper(ProfesorMapper profesorMapper, SecretaraMapper secretaraMapper) {
        this.profesorMapper = profesorMapper;
        this.secretaraMapper = secretaraMapper;
    }

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

    public List<UserResponseDTO> toResponseDTOList(List<User> useri) {
        List<UserResponseDTO> userDTOs = new ArrayList<>();
        for (User user : useri) {
            userDTOs.add(toResponseDTO(user));
        }
        return userDTOs;
    }

}