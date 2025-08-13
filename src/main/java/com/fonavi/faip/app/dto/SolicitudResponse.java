package com.fonavi.faip.app.dto;

import com.fonavi.faip.app.entity.EstadoSolicitud;

import java.time.LocalDate;

public record SolicitudResponse(
        String codigo,
        EstadoSolicitud estado,
        LocalDate fechaRegistro
) { }
