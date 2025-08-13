package com.fonavi.faip.app.service;


import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.dto.SolicitudUpdateEstadoRequest;
import com.fonavi.faip.app.entity.EstadoSolicitud;
import com.fonavi.faip.app.entity.Solicitud;
import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import com.fonavi.faip.app.repository.SolicitudRepository;
import com.fonavi.faip.app.repository.SolicitudSeguimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository repo;
    private final SolicitudSeguimientoRepository seguimientoRepo;

    //Contador en memoria (opcional si luego lo haces con base de datos)
    private static final AtomicInteger contador = new AtomicInteger(0);

    @Override
    public SolicitudResponse crear(SolicitudCreateRequest request) {
        Solicitud entidad = new Solicitud();

        // Mapear campos desde DTO
        entidad.setTipoSolicitante(request.tipoSolicitante());
        entidad.setTipoDocumento(request.tipoDocumento());
        entidad.setNumeroDocumento(request.numeroDocumento());

        entidad.setNombres(request.nombres());
        entidad.setApellidosPaterno(request.apellidos_paterno());
        entidad.setApellidosMaterno(request.apellidos_materno());
        entidad.setRazonSocial(request.razonSocial());
        entidad.setPais(request.pais() == null || request.pais().isBlank() ? "Perú" : request.pais());
        entidad.setDepartamento(request.departamento());
        entidad.setProvincia(request.provincia());
        entidad.setDistrito(request.distrito());
        entidad.setDireccion(request.direccion());


        entidad.setEmail(request.email());
        entidad.setTelefono(request.telefono());
        entidad.setEdad(request.edad());
        entidad.setSexo(request.sexo());

        entidad.setAreaPertenece(request.areaPertenece());
        entidad.setDescripcion(request.descripcion());
        entidad.setMedioEntrega(request.medioEntrega());
        entidad.setModalidadNotificacion(request.modalidadNotificacion());
        entidad.setArchivoAdjunto(request.archivoAdjunto());
        entidad.setObservaciones(request.observaciones());
        entidad.setAceptaTerminos(request.aceptaTerminos());
        // Estado inicial
        entidad.setEstado(EstadoSolicitud.REGISTRADA);
        // Fecha de registro
        entidad.setFechaRegistro(LocalDate.now());

        // 1️⃣ Guardar primero para generar el ID
        Solicitud guardada = repo.save(entidad);
        guardada = repo.save(guardada); // segundo save para actualizar con el código generado

        // Guardar seguimiento inicial
        guardarSeguimiento(guardada, EstadoSolicitud.REGISTRADA, "Solicitud registrada en el sistema", "Sistema");

        // Retornar respuesta resumida
        return mapToResponse(guardada);
    }

    @Override
    public SolicitudResponse porCodigo(String codigo) {
        Solicitud solicitud = repo.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
        return mapToResponse(solicitud);
    }


//------------------------------------------------------------------------------------------
    @Override
    public SolicitudResponse actualizarEstado(Long id, SolicitudUpdateEstadoRequest request) {
      Solicitud solicitud = repo.findById(id)
              .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
      solicitud.setEstado(request.estado());
      solicitud.setObservaciones(request.observacion());
      Solicitud guardada = repo.save(solicitud);

        // Guardar seguimiento del cambio de estado
        guardarSeguimiento(guardada, request.estado(), request.observacion(), "Administrador");

        return mapToResponse(guardada);
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


    //------------------------------------------------------------------------------------------
    private void guardarSeguimiento(Solicitud solicitud, EstadoSolicitud estado, String observacion, String usuario) {
        SolicitudSeguimiento seguimiento = new SolicitudSeguimiento();
        seguimiento.setSolicitud(solicitud);       // vínculo con la solicitud
        seguimiento.setEstado(estado);             // estado actual
        seguimiento.setObservacion(observacion);   // comentario o detalle
        seguimiento.setUsuarioAccion(usuario);     // quién hizo el cambio
        seguimiento.setFechaRegistro(LocalDateTime.now()); // fecha y hora del registro
        seguimientoRepo.save(seguimiento);         // persiste en la BD
    }

}
