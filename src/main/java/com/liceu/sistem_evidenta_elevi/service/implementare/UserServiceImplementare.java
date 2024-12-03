package com.liceu.sistem_evidenta_elevi.service.implementare;

import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import com.liceu.sistem_evidenta_elevi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementare implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementare(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        userActual.setIdUser(user.getIdUser());
        userActual.setUsername(user.getUsername());
        userActual.setParola(user.getParola());
        userActual.setEmail(user.getEmail());
        userActual.setRol(user.getRol());
        return userRepository.save(userActual);
    }

    @Override
    public User adaugaUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User  must not be null");
        }
        return userRepository.save(user);
    }

}
