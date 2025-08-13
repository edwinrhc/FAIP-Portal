package com.fonavi.faip.app.dto;

import jakarta.validation.constraints.*;

public record SolicitudCreateRequest(

        @NotBlank(message = "El tipo de solicitante es obligatorio")
        @Size(max = 100, message = "El tipo de solicitante no puede superar los 100 caracteres")
        String tipoSolicitante,


        @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
        String nombres,

        @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
        String apellidos,

        @Size(max = 150, message = "La razón social no puede superar los 150 caracteres")
        String razonSocial,

        @NotBlank(message = "El tipo de documento es obligatorio")
        @Size(max = 20, message = "El tipo de documento no puede superar los 20 caracteres")
        String tipoDocumento,

        @NotBlank(message = "El número de documento es obligatorio")
        @Size(min = 8, max = 20, message = "El número de documento debe tener entre 8 y 20 caracteres")
        @Pattern(regexp = "^[0-9A-Za-z]+$", message = "Documento inválido")
        String numeroDocumento,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        @Size(max = 150, message = "El email no puede superar los 150 caracteres")
        String email,

        @Pattern(regexp = "^[0-9 +()-]{6,20}$", message = "Teléfono inválido")
        String telefono,

        @Size(max = 255, message = "La dirección no puede superar los 255 caracteres")
        String direccion,

        @Size(max = 100, message = "El distrito no puede superar los 100 caracteres")
        String distrito,

        @Size(max = 100, message = "La provincia no puede superar los 100 caracteres")
        String provincia,

        @Size(max = 100, message = "El departamento no puede superar los 100 caracteres")
        String departamento,

        @Size(max = 100, message = "El país no puede superar los 100 caracteres")
        String pais,

        @NotBlank(message = "El medio de entrega es obligatorio")
        @Size(max = 20, message = "El medio de entrega no puede superar los 20 caracteres")
        String medioEntrega,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, max = 2000, message = "La descripción debe tener entre 10 y 2000 caracteres")
        String descripcion,

        @Size(max = 2000, message = "Las observaciones no pueden superar los 2000 caracteres")
        String observaciones,

        @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
        boolean aceptaTerminos
) {
}