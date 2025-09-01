package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.DepartamentoResponse;
import com.fonavi.faip.app.dto.DistritoResponse;
import com.fonavi.faip.app.dto.ProvinciaResponse;
import com.fonavi.faip.app.entity.Departamento;
import com.fonavi.faip.app.entity.Distrito;
import com.fonavi.faip.app.entity.Provincia;
import com.fonavi.faip.app.repository.DepartamentoRepository;
import com.fonavi.faip.app.repository.DistritoRepository;
import com.fonavi.faip.app.repository.ProvinciaRepository;
import com.fonavi.faip.app.service.UbigeoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubigeo")
@RequiredArgsConstructor
public class UbigeoRestController {

    private final UbigeoService ubigeoService;

    @GetMapping("/departamentos")
    public ResponseEntity<List<DepartamentoResponse>> getDepartamentos(){
        return ResponseEntity.ok(ubigeoService.getDepartamentos());
    }

    @GetMapping("/departamentos/{idDepartamento}/provincias")
    public ResponseEntity<List<ProvinciaResponse>> getProvincias(@PathVariable Long idDepartamento){
        return ResponseEntity.ok(ubigeoService.getProvincias(idDepartamento));
    }

    @GetMapping("/provincias/{idProvincia}/distritos")
    public ResponseEntity<List<DistritoResponse>> getDistrito(@PathVariable Long idProvincia){
        return ResponseEntity.ok(ubigeoService.getDistrito(idProvincia));
    }






}
