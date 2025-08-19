package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.AuthResponse;
import com.fonavi.faip.app.dto.LoginRequest;
import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest){

        User usuario = authenticationService.autenticar(
                loginRequest.username(),
                loginRequest.password()
        );

        String token = tokenService.generarToken(usuario);

    }
}
