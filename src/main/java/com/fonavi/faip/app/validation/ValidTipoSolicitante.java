package com.fonavi.faip.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SolicitudTipoSolicitanteValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface  ValidTipoSolicitante {

    String message() default "Datos inválidos según el tipo de solicitante";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
