package com.fonavi.faip.app.service;


import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.entity.EstadoSolicitud;
import com.fonavi.faip.app.entity.Solicitud;
import com.fonavi.faip.app.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService{

    private final SolicitudRepository repo;

    //Contador en memoria (opcional si luego lo haces con base de datos)
    private static final AtomicInteger contador = new AtomicInteger(0);

    @Override
    public SolicitudResponse crear(SolicitudCreateRequest request) {
        Solicitud entidad = new Solicitud();

        // Mapear campos desde DTO
        entidad.setTipoSolicitante(request.tipoSolicitante());
        entidad.setNombres(request.nombres());
        entidad.setApellidos(request.apellidos());
        entidad.setRazonSocial(request.razonSocial());
        entidad.setTipoDocumento(request.tipoDocumento());
        entidad.setNumeroDocumento(request.numeroDocumento());
        entidad.setEmail(request.email());
        entidad.setTelefono(request.telefono());
        entidad.setDireccion(request.direccion());
        entidad.setDistrito(request.distrito());
        entidad.setProvincia(request.provincia());
        entidad.setDepartamento(request.departamento());
        entidad.setPais(request.pais() == null || request.pais().isBlank() ? "Perú" : request.pais());
        entidad.setMedioEntrega(request.medioEntrega());
        entidad.setDescripcion(request.descripcion());
        entidad.setObservaciones(request.observaciones());
        entidad.setAceptaTerminos(request.aceptaTerminos());

        // Generar código único
        String codigo = generarCodigoUnico();
        entidad.setCodigo(codigo);

        // Estado inicial
        entidad.setEstado(EstadoSolicitud.REGISTRADA);

        // Fecha de registro
        entidad.setFechaRegistro(LocalDate.now());

        // Guardar en base de datos
        Solicitud guardada = repo.save(entidad);

        // Retornar respuesta resumida
        return mapToResponse(guardada);
    }

    @Override
    public SolicitudResponse porCodigo(String codigo) {
        Solicitud solicitud = repo.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        return mapToResponse(solicitud);
    }

    /**
     * Genera código con formato FAIP-YYYY-#### (ejemplo: FAIP-2025-0001)
     */
    private String generarCodigoUnico() {
        int secuencia = contador.incrementAndGet();
        String año = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return String.format("FAIP-%s-%04d", año, secuencia);
    }

    /**
     * Convierte la entidad a DTO de respuesta (versión resumida)
     */
    private SolicitudResponse mapToResponse(Solicitud s) {
        return new SolicitudResponse(
                s.getCodigo(),
                s.getEstado(),
                s.getFechaRegistro()
        );
    }

}
