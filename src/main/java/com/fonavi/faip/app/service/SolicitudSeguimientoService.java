package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.SeguimientoResponse;

import java.util.List;

public interface SolicitudSeguimientoService {
    List<SeguimientoResponse> listarSeguimientoPorCodigo(String codigo);

}
