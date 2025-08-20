package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.Role;
import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService{

    private final JwtUtil jwtUtil;

    public TokenServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @Override
    public String generarToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles",user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList()));
        return jwtUtil.generateToken(user.getUsername(), claims);
    }

    @Override
    public String obtenerUsernameDesdeToken(String token) {
        return jwtUtil.extractUsername(token);
    }

    @Override
    public boolean validarToken(String token) {
        // Validamos el username real
        try{
            String username = obtenerUsernameDesdeToken(token);
            return username != null;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String obtenerRoleDesdeToken(String token) {
        return TokenService.super.obtenerRoleDesdeToken(token);
    }
}
