package com.liceu.sistem_evidenta_elevi.entity;

public enum Rol {
    ROLE_ADMIN,
    ROLE_PROFESOR;

    public static boolean exista(String numeRol) {
        for (Rol rol : Rol.values()) {
            if(rol.name().equals(numeRol))
                return true;
        }
        return false;
    }
}