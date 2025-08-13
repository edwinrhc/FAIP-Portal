package com.fonavi.faip.app.validation;

import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SolicitudTipoSolicitanteValidator implements ConstraintValidator<ValidTipoSolicitante, SolicitudCreateRequest> {

    @Override
    public boolean isValid(SolicitudCreateRequest dto, ConstraintValidatorContext context) {
        if (dto == null) return true;

        boolean esNatural = "Persona Natural".equalsIgnoreCase(dto.tipoSolicitante());
        boolean esJuridica = "Persona Jurídica".equalsIgnoreCase(dto.tipoSolicitante());

        if (esNatural) {
            boolean nombresValidos = dto.nombres() != null && !dto.nombres().isBlank();
            boolean apPaternoValido = dto.apellidos_paterno() != null && !dto.apellidos_paterno().isBlank();
            boolean apMaternoValido = dto.apellidos_materno() != null && !dto.apellidos_materno().isBlank();

            if (!nombresValidos || !apPaternoValido || !apMaternoValido) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Para persona natural se requieren nombres y apellidos completos")
                        .addConstraintViolation();
                return false;
            }
        }

        if (esJuridica) {
            boolean razonValida = dto.razonSocial() != null && !dto.razonSocial().isBlank();

            if (!razonValida) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Para persona jurídica se requiere razón social")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}


