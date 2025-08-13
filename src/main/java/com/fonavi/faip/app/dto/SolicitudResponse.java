package com.fonavi.faip.app.dto;

import com.fonavi.faip.app.domain.EstadoSolicitud;

import java.time.LocalDate;

public record SolicitudResponse(
        String codigo,
        EstadoSolicitud estado,
        LocalDate fechaRegistro
) { }
