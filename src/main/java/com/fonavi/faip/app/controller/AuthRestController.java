package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.AuthResponse;
import com.fonavi.faip.app.dto.LoginRequest;
import com.fonavi.faip.app.dto.RegisterRequest;
import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.service.AuthenticationService;
import com.fonavi.faip.app.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthRestController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        User usuario = authenticationService.registrar(
                registerRequest.username(),
                registerRequest.password(),
                registerRequest.role()
        );
        String token = tokenService.generarToken(usuario);
        String rol = usuario.getRoles().stream()
                .findFirst()
                .map(r -> r.getName())
                .orElse("USER");
        return ResponseEntity.ok(new AuthResponse(token, rol));
    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@Valid @RequestBody LoginRequest loginRequest){
        User usuario = authenticationService.autenticar(
                loginRequest.username(),
                loginRequest.password()
        );

        String token = tokenService.generarToken(usuario);
        // Devolver el token y el rol
        String rol = usuario.getRoles().stream()
                .findFirst()
                .map(r -> r.getName())
                .orElse("USER");

        return ResponseEntity.ok(new AuthResponse(token, rol));
    }


}
