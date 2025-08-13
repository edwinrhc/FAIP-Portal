package com.fonavi.faip.app.dto;

import java.time.LocalDateTime;

public record SeguimientoResponse(
        LocalDateTime fecha,
        String estado,
        String observacion
) {
}
