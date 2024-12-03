package com.liceu.sistem_evidenta_elevi.service;

import com.liceu.sistem_evidenta_elevi.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUseri();
    User getUserById(Integer id);
    User adaugaUser(User user);
    User actualizeazaUser(User user);
}
