package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUseri();
    User getUserById(Integer id);
    User getUserByUsername(String username);
    User adaugaUser(UserRequestDTO userRequest);
    User actualizeazaUser(UserRequestDTO user);
    void stergeUser(Integer idUser);
}
