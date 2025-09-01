package com.fonavi.faip.app.dto;

import java.util.List;

public record ProvinciaResponse(
        Long id,
        String nombre,
        String ubigeo,
        List<DistritoResponse> distritos
) {
}
