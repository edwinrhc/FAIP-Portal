package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.SeguimientoResponse;
import com.fonavi.faip.app.service.SolicitudSeguimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/solicitudes/seguimiento")
@RequiredArgsConstructor
public class SolicitudSeguimientoRestController {

    private final SolicitudSeguimientoService seguimientoService;

    @GetMapping("/{codigo}")
    public List<SeguimientoResponse> obtenerSeguimiento(@PathVariable String codigo){
        return seguimientoService.listarSeguimientoPorCodigo(codigo);
    }

}
