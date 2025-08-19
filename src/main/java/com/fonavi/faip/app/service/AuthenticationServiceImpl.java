package com.fonavi.faip.app.service;

import com.fonavi.faip.app.entity.Permiso;
import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.exception.AuthorizationException;
import com.fonavi.faip.app.exception.InvalidCredentialsException;
import com.fonavi.faip.app.repository.PermisoRepository;
import com.fonavi.faip.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private PermisoRepository permisoRepository;


    public AuthenticationServiceImpl(UserRepository usuarioRepository, PasswordEncoder passwordEncoder, PermisoRepository permisoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.permisoRepository = permisoRepository;
    }

    @Override
    public User autenticar(String username, String password) {
        User usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Usuario no encontrado"));

        if(!passwordEncoder.matches(password, usuario.getPassword())){
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }

        return usuario;
    }

    @Override
    public void verificarAutorizacion(User usuario, String recurso) {
        boolean tienePermiso = usuario.getRoles().stream()
                .anyMatch(rol -> permisoRepository.findByRolAndRecurso(rol, recurso).stream().anyMatch(Permiso::isAcceso));

        if (!tienePermiso) {
            throw new AuthorizationException(
                    "El usuario no tiene permisos para acceder al recurso: " + recurso);
        }
    }




}
