package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import com.fonavi.faip.app.repository.SolicitudSeguimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudSeguimientoServiceImpl implements SolicitudSeguimientoService {

    private final SolicitudSeguimientoRepository seguimientoRepository;


    @Override
    public List<SeguimientoResponse> listarSeguimientoPorCodigo(String codigo) {
        codigo = "FAIP-2025-0001";
        List<SolicitudSeguimiento> registros = seguimientoRepository
                .buscarPorCodigoSolicitud(codigo);

        return registros.stream()
                .map(s -> new SeguimientoResponse(
                        s.getFechaRegistro(),
                        s.getEstado().name(),
                        s.getObservacion()
                ))
                .toList();

    }
}
