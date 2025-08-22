package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.dto.SolicitudUpdateEstadoRequest;
import com.fonavi.faip.app.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
public class SolicitudRestController {

    private final SolicitudService service;

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear nueva solicitud de acceso a la información",
    description = "Permite a un ciudadano registrar una solicitud con sus datos personales y descripción")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada"),
        @ApiResponse(responseCode = "400", description = "Datos Inválidos")
    })
    public ResponseEntity<SolicitudResponse> crear(
            @Valid @RequestBody SolicitudCreateRequest request){
        SolicitudResponse response = service.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudResponse> actualizarEstado(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudUpdateEstadoRequest request) {
        return ResponseEntity.ok(service.actualizarEstado(id, request));
    }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    /**
     * Obtiene una solicitud por su código
     */
    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudResponse porCodigo(@PathVariable String codigo) {
        return service.porCodigo(codigo);
    }

}
