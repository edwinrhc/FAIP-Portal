package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenServiceImpl implements TokenService{

    private final JwtUtil jwtUtil;

    @Autowired
    public TokenServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String generarToken(Usuario usuario) {
        // Asume que JwtUtil tiene un método createToken o similar que recibe username y roles
        // y devuelve un JWT firmado.
        String username = usuario.getUsername();
        // Si necesitas incluir roles/claims, añade aquí la lógica o un DTO de claims.
        return jwtUtil.generateToken(username, usuario.getRoles());
    }

    @Override
    public String obtenerUsernameDesdeToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    @Override
    public boolean validarToken(String token) {
        return jwtUtil.validateToken(token);
    }

}
