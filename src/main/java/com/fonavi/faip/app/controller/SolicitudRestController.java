package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.service.SolicitudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/solicitudes")
@RequiredArgsConstructor
public class SolicitudRestController {

    private final SolicitudService service;

    /**
     * Crea una nueva solicitud
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public SolicitudResponse crear(@Valid @RequestBody SolicitudCreateRequest request) {
        return service.crear(request);
    }

    /**
     * Obtiene una solicitud por su código
     */
    @GetMapping(value = "/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudResponse porCodigo(@PathVariable String codigo) {
        return service.porCodigo(codigo);
    }

}
