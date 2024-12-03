package com.liceu.sistem_evidenta_elevi.controller;


import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("liceu/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserController() {}

    @GetMapping
    public ResponseEntity<List<User>> getUseri() {
        List<User> useri = userService.getAllUseri();
        return new ResponseEntity<>(useri, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Integer idUser) {
        User user = userService.getUserById(idUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> adaugaUser(@RequestBody User user) {
        User userSalvat = userService.adaugaUser(user);
        return new ResponseEntity<>(userSalvat, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> actualizeazaUser(@PathVariable("id") Integer idUser, @RequestBody User user) {
        user.setIdUser(idUser);
        User userActualizat = userService.actualizeazaUser(user);
        return new ResponseEntity<>(userActualizat, HttpStatus.OK);
    }

}
