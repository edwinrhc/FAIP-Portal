package com.fonavi.faip.app.dto;

import jakarta.validation.constraints.*;

public record SolicitudCreateRequest(

        @NotBlank(message = "El tipo de solicitante es obligatorio")
        @Size(max = 100, message = "El tipo de solicitante no puede superar los 100 caracteres")
        String tipoSolicitante,



        @Size(max = 100) String nombres,
        @Size(max = 100) String apellidos_paterno,
        @Size(max = 100) String apellidos_materno,

        @Size(max = 150) String razonSocial,

        @NotBlank(message = "El tipo de documento es obligatorio")
        @Size(max = 20, message = "El tipo de documento no puede superar los 20 caracteres")
        String tipoDocumento,

        @NotBlank(message = "El número de documento es obligatorio")
        @Size(min = 8, max = 20, message = "El número de documento debe tener entre 8 y 20 caracteres")
        @Pattern(regexp = "^[0-9A-Za-z]+$", message = "Documento inválido")
        String numeroDocumento,

        @Size(max = 100) String pais,
        @Size(max = 100) String departamento,
        @Size(max = 100) String provincia,
        @Size(max = 100) String distrito,
        @Size(max = 255) String direccion,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        @Size(max = 150)
        String email,

        @Pattern(regexp = "^[0-9 +()-]{6,20}$", message = "Teléfono inválido")
        String telefono,


        Integer edad,

        @Size(max = 20) String sexo,
        @Size(max = 100)String areaPertenece,


        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, max = 2000)
        String descripcion,

        @NotBlank(message = "El medio de entrega es obligatorio")
        @Size(max = 20)
        String medioEntrega,

        @NotBlank(message = "La modalidad de notificación es obligatoria")
        String modalidadNotificacion,

        byte[] archivoAdjunto,

        @Size(max = 2000)
        String observaciones,

        @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
        boolean aceptaTerminos
) {
}