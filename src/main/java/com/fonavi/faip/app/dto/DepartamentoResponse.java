package com.fonavi.faip.app.dto;

import java.util.List;

public record DepartamentoResponse(
        Long id,
        String nombre,
        String ubigeo,
        List<ProvinciaResponse> provincias

) {
}
