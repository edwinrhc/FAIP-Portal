package com.fonavi.faip.app.dto;

import com.fonavi.faip.app.entity.EstadoSolicitud;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitudUpdateEstadoRequest(

        @NotNull(message = "El estado es obligatorio")
        EstadoSolicitud estado,

        @Size(max = 200, message = "La observación no puede superar los 2000 caracteres")
        String observacion){}
