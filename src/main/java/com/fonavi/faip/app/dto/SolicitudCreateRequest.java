package com.fonavi.faip.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SolicitudCreateRequest(
        @NotBlank String tipoSolicitante,
        @NotBlank String nombres,
        String apellidos,
        String razonSocial,
        @NotBlank String tipoDocumento,
        @NotBlank String numeroDocumento,
        @NotBlank @Email String email,
        String telefono,
        @NotBlank String medioEntrega,
        @NotBlank @Size(min = 10) String descripcion,
        String observaciones
) {}