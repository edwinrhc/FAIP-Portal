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

        @NotBlank(message = "El pais es obligatorio")
        @Size(max = 100) String pais,
  /*      @Size(max = 100) String departamento,
        @Size(max = 100) String provincia,
        @Size(max = 100) String distrito,*/
        Long departamento,
        Long provincia,
        Long distrito,

        @Size(max = 300) String direccion,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "Debe ser un email válido")
        @Size(max = 150)
        String email,

        @Pattern(regexp = "^[0-9 +()-]{6,20}$", message = "Teléfono inválido")
        String telefono,

        Integer edad,
        @Size(max = 20) String sexo,
        @Size(max = 250) String autoidentificacionEtnica,
        String lenguaMaterna,
        String discapacidad,
        @Size(max = 150) String areaGeografica,

// Paso 02

        @Size(max = 100)String areaPertenece,

        @NotBlank(message = "La descripción es obligatoria")
        @Size(min = 10, max = 2000)
        String descripcion,

        @Size(max = 2000)
        String observaciones,

        byte[] archivoAdjunto,

        @NotBlank(message = "El medio de entrega es obligatorio")
        @Size(max = 20)
        String medioEntrega,
        @Size(max = 100)
        String OtromedioEntrega,

        @NotBlank(message = "La modalidad de notificación es obligatoria")
        String modalidadNotificacion,
        @Size(max = 100)
        String OtroModalidadNotificacion,


        @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
        boolean aceptaTerminos

) {

    @AssertTrue(message="Para Perú debes enviar departamento, provincia y distrito; si no es Perú, déjalos vacíos.")
    public boolean isUbicacionValida(){
        if(pais == null | pais.isBlank()) return false;
        boolean esPeru = pais.equalsIgnoreCase("Perú") || pais.equalsIgnoreCase("Peru");
        boolean tieneGeo = departamento != null && provincia != null && distrito != null;
        boolean sinGEo = departamento == null && provincia == null && distrito == null;
        return esPeru ? tieneGeo : sinGEo;
    }

}