package com.fonavi.faip.app.dto;

import com.fonavi.faip.app.entity.EstadoSolicitud;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record SolicitudResponse(
        String codigo,
        EstadoSolicitud estado,
        LocalDate fechaRegistro
) { }
