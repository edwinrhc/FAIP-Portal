package com.fonavi.faip.app.exception;

import org.springframework.security.core.AuthenticationException;


public class AuthorizationException extends AuthenticationException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

}
