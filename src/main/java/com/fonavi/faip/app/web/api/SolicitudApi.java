package com.fonavi.faip.app.web.api;

import com.fonavi.faip.app.dto.SolicitudCreateRequest;
import com.fonavi.faip.app.dto.SolicitudResponse;
import com.fonavi.faip.app.service.SolicitudService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/solicitudes")
public class SolicitudApi {

    private final SolicitudService service;

    public SolicitudApi(SolicitudService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolicitudResponse> crear(@Valid @RequestBody SolicitudCreateRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
    }


    @GetMapping(value="/{codigo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SolicitudResponse porCodigo(@PathVariable String codigo) {
        return service.porCodigo(codigo);
    }


}
