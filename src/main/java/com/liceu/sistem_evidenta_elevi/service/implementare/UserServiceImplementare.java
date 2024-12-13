package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ProfesorRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Profesor;
import com.liceu.sistem_evidenta_elevi.entity.Rol;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.service.ProfesorService;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementare implements UserService {

    private UserRepository userRepository;
    private ProfesorService profesorService; // pentru a adauga profesorul asociat user-ului

    @Autowired
    public UserServiceImplementare(UserRepository userRepository, ProfesorService profesorService) {
        this.userRepository = userRepository;
        this.profesorService = profesorService;
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
        userActual.setParola(user.getParola());
        userActual.setEmail(user.getEmail());
        userActual.setRol(user.getRol());
        return userRepository.save(userActual);
    }

    @Override
    public User adaugaUser(UserRequestDTO userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("User nu poate fi null");
        }

        // creare user nou din DTO
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setParola(userRequest.getParola());
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
                ProfesorRequestDTO profesorRequest = userRequest.getProfesor();
                // adaugare profesor in baza de date

                // obtinem profesorul adaugat anterior
                Profesor profesor = profesorService.adaugaProfesor(profesorRequest);

                // facem legatura intre user si profesor
                profesor.setUser(user);
                user.setProfesor(profesor);
                break;
            // TODO adaugare cazuri si pentru celelalte roluri
            default:
                throw new IllegalArgumentException("Rol invalid: " + userRequest.getRol());
        }

        return userRepository.save(user);
    }

}
