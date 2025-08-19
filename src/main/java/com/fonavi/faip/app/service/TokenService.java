package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.User;

public interface TokenService {

    String generarToken(User user);
    String obtenerUsernameDesdeToken(String token);
    boolean validarToken(String token);

    // Opcional: obtener Usuario completo a partir del token
    default String obtenerRoleDesdeToken(String token) {
        // si tu JwtUtil expone claims, puedes extraer el rol aquí
        return null;
    }

}
