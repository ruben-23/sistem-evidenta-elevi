package com.liceu.sistem_evidenta_elevi.controller;

import com.liceu.sistem_evidenta_elevi.dto.UserRequestDTO;
import com.liceu.sistem_evidenta_elevi.dto.UserResponseDTO;
import com.liceu.sistem_evidenta_elevi.entity.User;
import com.liceu.sistem_evidenta_elevi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/liceu/autentificare")
public class AutentificareController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Autowired
    public AutentificareController(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO userRequest) {
        try {
            // autentificare user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userRequest.getUsername(),
                            userRequest.getParola()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // returnare user autentificat(principal)
            org.springframework.security.core.userdetails.User principal =
                    (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            // returnare user din baza de date
            String username = principal.getUsername();
            User userAutentificat = userRepository.findByUsername(username);

            // creare DTO pentru a trimite raspunsul
            UserResponseDTO userDTO = new UserResponseDTO();
            userDTO.setIdUser(userAutentificat.getIdUser());
            userDTO.setUsername(userAutentificat.getUsername());
            userDTO.setEmail(userAutentificat.getEmail());
            userDTO.setRol(userAutentificat.getRol().name());

            return ResponseEntity.ok(userDTO);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }


}
