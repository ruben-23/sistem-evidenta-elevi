package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.dto.ElevRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.Elev;
import com.liceu.sistem_evidenta_elevi.entity.Rol;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.service.ElevService;
import com.liceu.sistem_evidenta_elevi.service.RolService;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementare implements UserService {

    private UserRepository userRepository;
    private RolService rolService; // pentru a putea accesa rolurile
    private ElevService elevService; // pentru a adauga elevul asociat user-ului

    @Autowired
    public UserServiceImplementare(UserRepository userRepository, RolService rolService, ElevService elevService) {
        this.userRepository = userRepository;
        this.rolService = rolService;
        this.elevService = elevService;
    }

    @Override
    public List<User> getAllUseri() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public User actualizeazaUser(User user) {
        User userActual = userRepository.findById(user.getIdUser()).get();
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

        // verifica daca rolul exista
        Rol rol = rolService.getRolByNume(userRequest.getRol());
        if(rol == null) {
            throw new IllegalArgumentException("Rolul nu exista");
        }

        // rolul obtinut e atribuit user-ului
        user.setRol(rol);

        // creare entitate in functie de rolul din user
        switch(userRequest.getRol().toLowerCase()) {

            case "elev":
                // obtinem elevDTO din userDTO
                ElevRequestDTO elevRequest = userRequest.getElev();
                // adaugare elev in baza de date
                elevService.adaugaElev(elevRequest);

                // obtinem elevul adaugat anterior
                Elev elev = elevService.getElevById(elevRequest.getIdElev());

                // facem legatura intre user si elev
                elev.setUser(user);
                user.setElev(elev);
                break;
            // adaugare cazuri si pentru celelalte roluri
            default:
                throw new IllegalArgumentException("Rol invalid: " + userRequest.getRol());
        }

        return userRepository.save(user);
    }

}
