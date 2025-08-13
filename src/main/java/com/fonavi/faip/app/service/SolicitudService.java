package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.dto.SolicitudUpdateEstadoRequest;

import java.util.List;

public interface SolicitudService {

    SolicitudResponse crear(SolicitudCreateRequest request);
    SolicitudResponse porCodigo(String codigo);
    SolicitudResponse actualizarEstado(Long id, SolicitudUpdateEstadoRequest request);

}
