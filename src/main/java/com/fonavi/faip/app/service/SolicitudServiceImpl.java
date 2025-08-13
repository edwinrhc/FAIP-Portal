package com.fonavi.faip.app.service;


import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService{

    private final SolicitudRepository repo;

    @Override
    public SolicitudResponse crear(SolicitudCreateRequest solicitud) {
        return null;
    }

    @Override
    public SolicitudResponse porCodigo(String codigo) {
        return null;
    }
}
