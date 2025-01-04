package com.liceu.sistem_evidenta_elevi.entity;

public enum Rol {
    ROLE_PROFESOR,
    ROLE_SECRETARA;

    public static boolean exista(String numeRol) {
        for (Rol rol : Rol.values()) {
            if(rol.name().equals(numeRol))
                return true;
        }
        return false;
    }
}