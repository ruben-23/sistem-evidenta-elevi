package com.liceu.sistem_evidenta_elevi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void creareUserTest(){
        User user = new User();
        user.setIdUser(1);
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");

        assertNotNull(user);
        assertEquals(1, user.getIdUser());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("email", user.getEmail());

    }

}
