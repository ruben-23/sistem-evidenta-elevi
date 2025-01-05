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

/**
 * Implementare a serviciului User.
 * Contine metode pentru gestionarea operatiunilor legate de utilizatori.
 */
@Service
public class UserServiceImplementare implements UserService {

    private final UserRepository userRepository;
    private final ProfesorService profesorService;
    private final SecretaraService secretaraService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor pentru injectarea dependintelor.
     *
     * @param userRepository Repositorul pentru gestionarea utilizatorilor.
     * @param profesorService Serviciul pentru gestionarea profesorilor.
     * @param passwordEncoder Encoderul pentru criptarea parolelor utilizatorilor.
     * @param secretaraService Serviciul pentru gestionarea secretarelor.
     */
    @Autowired
    public UserServiceImplementare(UserRepository userRepository, ProfesorService profesorService,
                                   PasswordEncoder passwordEncoder, SecretaraService secretaraService) {
        this.userRepository = userRepository;
        this.profesorService = profesorService;
        this.passwordEncoder = passwordEncoder;
        this.secretaraService = secretaraService;
    }

    /**
     * Obtine toti utilizatorii din sistem.
     *
     * @return Lista tuturor utilizatorilor.
     */
    @Override
    public List<User> getAllUseri() {
        return userRepository.findAll();
    }

    /**
     * Obtine un utilizator dupa ID.
     *
     * @param id ID-ul utilizatorului.
     * @return Utilizatorul corespunzator ID-ului.
     * @throws RuntimeException Daca utilizatorul nu este gasit.
     */
    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Userul nu a fost gasit"));
    }

    /**
     * Obtine un utilizator dupa username.
     *
     * @param username Numele de utilizator.
     * @return Utilizatorul corespunzator username-ului.
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Actualizeaza informatiile unui utilizator.
     *
     * @param user DTO-ul care contine datele actualizate ale utilizatorului.
     * @return Utilizatorul actualizat.
     */
    @Transactional
    @Override
    public User actualizeazaUser(UserRequestDTO user) {
        User userActual = getUserById(user.getIdUser());
        userActual.setUsername(user.getUsername());
        userActual.setParola(passwordEncoder.encode(user.getParola()));
        userActual.setEmail(user.getEmail());
        userActual.setRol(Rol.valueOf(user.getRol()));

        // Actualizeaza profesor sau secretara daca este cazul
        if (user.getProfesor() != null) {
            user.getProfesor().setIdUser(user.getIdUser());
            profesorService.actualizareProfesor(user.getProfesor());
        } else if (user.getSecretara() != null) {
            user.getSecretara().setIdUser(user.getIdUser());
            secretaraService.actualizareSecretara(user.getSecretara());
        }

        return userRepository.save(userActual);
    }

    /**
     * Adauga un nou utilizator in sistem.
     *
     * @param userRequest DTO-ul care contine datele utilizatorului de adaugat.
     * @return Utilizatorul adaugat.
     * @throws IllegalArgumentException Daca rolul nu este valid.
     */
    @Transactional
    @Override
    public User adaugaUser(UserRequestDTO userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("User nu poate fi null");
        }

        // Creaza un utilizator nou
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setParola(passwordEncoder.encode(userRequest.getParola()));
        user.setEmail(userRequest.getEmail());

        // Verifica daca rolul exista in enum
        String numeRol = userRequest.getRol().toUpperCase();
        if (!Rol.exista(numeRol)) {
            throw new IllegalArgumentException("Rolul nu exista");
        }

        // Atribuie rolul utilizatorului
        Rol rol = Rol.valueOf(numeRol);
        user.setRol(rol);

        // Creaza entitate in functie de rolul utilizatorului
        switch (rol) {
            case ROLE_PROFESOR:
                ProfesorDTO profesorRequest = userRequest.getProfesor();
                Profesor profesor = profesorService.adaugaProfesor(profesorRequest, user);
                user.setProfesor(profesor);
                break;

            case ROLE_SECRETARA:
                SecretaraDTO secretaraDTO = userRequest.getSecretara();
                Secretara secretara = secretaraService.adaugaSecretara(secretaraDTO, user);
                user.setSecretara(secretara);
                break;

            default:
                throw new IllegalArgumentException("Rol invalid: " + userRequest.getRol());
        }

        return userRepository.save(user);
    }

    /**
     * Sterge un utilizator din sistem pe baza ID-ului.
     *
     * @param idUser ID-ul utilizatorului de sters.
     */
    @Override
    public void stergeUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }
}