package com.fonavi.faip.app.validation;

import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SolicitudTipoSolicitanteValidator implements ConstraintValidator<ValidTipoSolicitante, SolicitudCreateRequest> {


    @Override
    public boolean isValid(SolicitudCreateRequest value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // Validar Persona Natural
        if ("Persona Natural".equalsIgnoreCase(value.tipoSolicitante())) {
            boolean valido = value.nombres() != null && !value.nombres().isBlank()
                    && value.apellidos() != null && !value.apellidos().isBlank();

            if (!valido) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Nombres y apellidos son obligatorios para Persona Natural"
                ).addConstraintViolation();
            }
            return valido;
        }

        // Validar Persona Jurídica
        if ("Persona Jurídica".equalsIgnoreCase(value.tipoSolicitante())) {
            boolean valido = value.razonSocial() != null && !value.razonSocial().isBlank();

            if (!valido) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Razón social es obligatoria para Persona Jurídica"
                ).addConstraintViolation();
            }
            return valido;
        }
        return true;
    }
}


