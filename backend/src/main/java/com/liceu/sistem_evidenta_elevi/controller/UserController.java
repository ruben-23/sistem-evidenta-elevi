package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserResponseDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.mapper.UserMapper;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pentru gestionarea operatiunilor CRUD asupra utilizatorilor.
 * Această clasă contine metode pentru adăugarea, actualizarea, stergerea si afisarea
 * informatiilor despre utilizatori.
 */
@RestController
@RequestMapping("liceu/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor pentru injectarea dependentelor.
     *
     * @param userService Serviciul pentru gestionarea operatiunilor legate de utilizatori.
     * @param userMapper Mapper pentru conversia dintre entităti si DTO-uri.
     */
    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Endpoint pentru obtinerea listei de utilizatori.
     *
     * @return O listă de utilizatori sub formă de {@link UserResponseDTO}.
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUseri() {
        List<User> useri = userService.getAllUseri();
        return ResponseEntity.ok(userMapper.toResponseDTOList(useri));
    }

    /**
     * Endpoint pentru obtinerea detaliilor unui utilizator specific.
     *
     * @param idUser ID-ul utilizatorului căutat.
     * @return Detaliile utilizatorului sub formă de {@link UserResponseDTO}.
     */
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Integer idUser) {
        User user = userService.getUserById(idUser);
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    /**
     * Endpoint pentru adăugarea unui nou utilizator.
     *
     * @param userRequest DTO-ul care contine informatiile utilizatorului ce terbuie adăugat.
     * @return Utilizatorul salvat sub formă de {@link UserResponseDTO}.
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> adaugaUser(@RequestBody UserRequestDTO userRequest) {
        User userSalvat = userService.adaugaUser(userRequest);
        return ResponseEntity.ok(userMapper.toResponseDTO(userSalvat));
    }

    /**
     * Endpoint pentru actualizarea informatiilor unui utilizator existent.
     *
     * @param idUser ID-ul utilizatorului de actualizat.
     * @param user DTO-ul care contine noile informatii ale utilizatorului.
     * @return Utilizatorul actualizat sub formă de {@link UserResponseDTO}.
     */
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> actualizeazaUser(@PathVariable("id") Integer idUser, @RequestBody UserRequestDTO user) {
        user.setIdUser(idUser);
        User userActualizat = userService.actualizeazaUser(user);
        return ResponseEntity.ok(userMapper.toResponseDTO(userActualizat));
    }

    /**
     * Endpoint pentru stergerea unui utilizator.
     *
     * @param idUser ID-ul utilizatorului de sters.
     * @return Un răspuns HTTP 204 No Content dacă operatiunea a fost efectuată cu succes.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id") Integer idUser) {
        userService.stergeUser(idUser);
        return ResponseEntity.noContent().build();
    }

}
