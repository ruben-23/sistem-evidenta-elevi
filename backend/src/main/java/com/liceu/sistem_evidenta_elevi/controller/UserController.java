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

@RestController
@RequestMapping("liceu/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUseri() {
        List<User> useri = userService.getAllUseri();
        return ResponseEntity.ok(userMapper.toResponseDTOList(useri));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") Integer idUser) {
        User user = userService.getUserById(idUser);
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> adaugaUser(@RequestBody UserRequestDTO userRequest) {
        User userSalvat = userService.adaugaUser(userRequest);
        return ResponseEntity.ok(userMapper.toResponseDTO(userSalvat));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> actualizeazaUser(@PathVariable("id") Integer idUser, @RequestBody UserRequestDTO user) {
        user.setIdUser(idUser);
        User userActualizat = userService.actualizeazaUser(user);
        return ResponseEntity.ok(userMapper.toResponseDTO(userActualizat));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable("id") Integer idUser) {
        userService.stergeUser(idUser);
        return ResponseEntity.noContent().build();
    }

}
