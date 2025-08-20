package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import com.fonavi.faip.app.repository.SolicitudSeguimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudSeguimientoServiceImpl implements SolicitudSeguimientoService {

    private final SolicitudSeguimientoRepository seguimientoRepository;


    @Override
    public List<SeguimientoResponse> listarSeguimientoPorCodigo(String codigo) {
        List<SolicitudSeguimiento> registros = seguimientoRepository
                .buscarPorCodigoSolicitud(codigo);

        //Validamos
        if(registros.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron registros para el código "+ codigo);
        }

        return registros.stream()
                .map(s -> new SeguimientoResponse(
                        s.getFechaRegistro(),
                        s.getEstado().name(),
                        s.getObservacion()
                ))
                .toList();

    }
}
