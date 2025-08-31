package com.fonavi.faip.app.controller;

import com.fonavi.faip.app.dto.DepartamentoResponse;
import com.fonavi.faip.app.entity.Departamento;
import com.fonavi.faip.app.entity.Distrito;
import com.fonavi.faip.app.entity.Provincia;
import com.fonavi.faip.app.repository.DepartamentoRepository;
import com.fonavi.faip.app.repository.DistritoRepository;
import com.fonavi.faip.app.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ubigeo")
@RequiredArgsConstructor
public class UbigeoRestController {

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @GetMapping("/departamentos")
    public List<DepartamentoResponse> listarDepartamentos(){
        return departamentoRepository
                .findAll()
                .stream()
                .map(dep -> new DepartamentoResponse(
                        dep.getId(),
                        dep.getNombre(),
                        dep.getUbigeo()
                )).toList();

    }

    @GetMapping("/provincias/{id}/provincias")
    public List<Provincia> listarProvincias(@PathVariable Integer id){
    return provinciaRepository.findByDepartamento_Id(id);
    }

    @GetMapping("/provincias/{id}/distritos")
    public List<Distrito> listarDistritos(@PathVariable Integer id){
    return distritoRepository.findByProvincia_Id(id);
    }


}
