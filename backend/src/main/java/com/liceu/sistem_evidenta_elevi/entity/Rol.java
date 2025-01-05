package com.liceu.sistem_evidenta_elevi.entity;

/**
 * Enum care reprezinta rolurile utilizatorilor in sistem.
 */
public enum Rol {
    ROLE_PROFESOR,
    ROLE_SECRETARA;

    /**
     * Verifica daca un rol exista in enum.
     *
     * @param numeRol Numele rolului de verificat.
     * @return true daca rolul exista, false in caz contrar.
     */
    public static boolean exista(String numeRol) {
        for (Rol rol : Rol.values()) {
            if(rol.name().equals(numeRol))
                return true;
        }
        return false;
    }
}