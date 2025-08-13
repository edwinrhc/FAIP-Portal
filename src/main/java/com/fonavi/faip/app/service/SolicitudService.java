package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;

public interface SolicitudService {

    SolicitudResponse crear(SolicitudCreateRequest request);
    SolicitudResponse porCodigo(String codigo);

}
