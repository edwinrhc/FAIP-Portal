package com.fonavi.faip.app.service;


import com.fonavi.faip.app.entity.User;
import com.fonavi.faip.app.exception.InvalidCredentialsException;

/**
 * Interfaz que define los servicios de autenticacion y autorizacion
 */
public interface AuthenticationService {

    /**
     * Autentica un usuario verificando sus credenciales
     * @param username Nombre de usuario
     * @param password Contraseña
     * @return Usuario Autenticado
     * @throws InvalidCredentialsException Si las credenciales son inválidas
     */
    User autenticar(String username, String password);

    User registrar(String username, String password, String roleName);


    /**
     * Verifica si un usuairo tiene los permisos necesarios para acceder a un recurso
     * @param user Usuario a verificar
     * @param recurso Recurso al que intenta acceder
     * @throws com.fonavi.faip.app.exception.AuthorizationException
     */
    void verificarAutorizacion(User user, String recurso);

}
