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
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @GetMapping("/departamentos")
    public List<DepartamentoResponse> listarDepartamentos(){
        long t0 = System.nanoTime();
        List<DepartamentoResponse> out = ubigeoService.getDepartamentos();
        long t1 = System.nanoTime();
        LoggerFactory.getLogger(getClass()).info("listarDepartamentos: {}ms", (t1-t0)/1000000);
        return out;

    }

    @GetMapping("/provincias/{departamentoId}")
    public List<ProvinciaResponse> listarProvincias(@PathVariable Integer departamentoId){
    return provinciaRepository.findByDepartamento_Id(departamentoId)
            .stream()
            .map(prov -> new ProvinciaResponse(
                    prov.getId(),
                    prov.getNombre(),
                    prov.getUbigeo()
            ))
            .toList();
    }

    @GetMapping("/distritos/{provinciaId}")
    public List<DistritoResponse> listarDistritos(@PathVariable Integer provinciaId){
    return distritoRepository.findByProvincia_Id(provinciaId)
            .stream()
            .map(dis -> new DistritoResponse(
                    dis.getId(),
                    dis.getNombre(),
                    dis.getUbigeo()
            )).toList();
    }


}
