package com.fonavi.faip.app.service;

import com.fonavi.faip.app.domain.EstadoSolicitud;
import com.fonavi.faip.app.domain.Solicitud;
import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.repo.SolicitudRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;

@Service
public class SolicitudService {

    private final SolicitudRepository repo;

    public SolicitudService(SolicitudRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public SolicitudResponse crear(SolicitudCreateRequest in){
        var s = new Solicitud();
        s.setFechaRegistro(LocalDate.now());
        s.setEstado(EstadoSolicitud.REGISTRADA);

        s.setTipoSolicitante(in.tipoSolicitante());
        s.setNombres(in.nombres());
        s.setApellidos(in.apellidos());
        s.setRazonSocial(in.razonSocial());
        s.setTipoDocumento(in.tipoDocumento());
        s.setNumeroDocumento(in.numeroDocumento());
        s.setEmail(in.email());
        s.setTelefono(in.telefono());
        s.setMedioEntrega(in.medioEntrega());
        s.setDescripcion(in.descripcion());
        s.setObservaciones(in.observaciones());

        // Generar código AIP-YYYY-##### con contador del año
        int year = Year.now().getValue();
        long seq = repo.countByYear(year) + 1;
        String codigo = String.format("AIP-%d-%06d", year, seq);
        s.setCodigo(codigo);

        var saved = repo.save(s);
        return new SolicitudResponse(saved.getCodigo(), saved.getEstado(), saved.getFechaRegistro());

    }

    public SolicitudResponse porCodigo(String codigo){
        var s = repo.findByCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada: "+ codigo));
        return new SolicitudResponse(s.getCodigo(), s.getEstado(), s.getFechaRegistro());
    }

}
