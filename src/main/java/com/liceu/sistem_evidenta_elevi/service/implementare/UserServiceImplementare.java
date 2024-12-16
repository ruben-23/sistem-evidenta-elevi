package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorDTO;
import com.liceu.sistem_evidenta_elevi.dto.SecretaraDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.Rol;
import com.liceu.sistem_evidenta_elevi.entity.Secretara;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.SecretaraService;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserServiceImplementare implements UserService {

    private final SecretaraService secretaraService;
    private UserRepository userRepository;
    private ProfesorService profesorService; // pentru a adauga profesorul asociat user-ului
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplementare(UserRepository userRepository, ProfesorService profesorService,
                                   PasswordEncoder passwordEncoder, SecretaraService secretaraService) {
        this.userRepository = userRepository;
        this.profesorService = profesorService;
        this.passwordEncoder = passwordEncoder;
        this.secretaraService = secretaraService;
    }

    @Override
    public List<User> getAllUseri() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Userul nu a fost gasit"));
    }

    @Override
    public User actualizeazaUser(User user) {
        User userActual = getUserById(user.getIdUser());
        userActual.setUsername(user.getUsername());
        userActual.setParola(passwordEncoder.encode(user.getParola()));
        userActual.setEmail(user.getEmail());
        userActual.setRol(user.getRol());
        return userRepository.save(userActual);
    }

    @Transactional
    @Override
    public User adaugaUser(UserRequestDTO userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("User nu poate fi null");
        }

        // creare user nou din DTO
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setParola(passwordEncoder.encode(userRequest.getParola()));
        user.setEmail(userRequest.getEmail());

        // verifica daca rolul exista in enum
        String numeRol = userRequest.getRol().toUpperCase();
        if(!Rol.exista(numeRol)) {
            throw new IllegalArgumentException("Rolul nu exista");
        }

        // rolul obtinut e atribuit user-ului
        Rol rol = Rol.valueOf(numeRol);
        user.setRol(rol);

        // creare entitate in functie de rolul din user
        switch(rol) {

            case ROLE_PROFESOR:
                // obtinem profesorDTO din userDTO
                ProfesorDTO profesorRequest = userRequest.getProfesor();
                profesorRequest.setIdUser(user.getIdUser());

                // obtinem profesorul dupa adaugare
                Profesor profesor = profesorService.adaugaProfesor(profesorRequest);

                // legatura intre user si profesor
                profesor.setUser(user);
                user.setProfesor(profesor);
                break;

            case ROLE_SECRETARA:
                SecretaraDTO secretaraDTO = userRequest.getSecretara();
                secretaraDTO.setIdUser(user.getIdUser());

                Secretara secretara = secretaraService.adaugaSecretara(secretaraDTO);
                secretara.setUser(user);
                user.setSecretara(secretara);
                break;

            default:
                throw new IllegalArgumentException("Rol invalid: " + userRequest.getRol());
        }

        return userRepository.save(user);
    }

}
