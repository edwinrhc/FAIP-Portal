package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.DepartamentoResponse;
import com.fonavi.faip.app.dto.DistritoResponse;
import com.fonavi.faip.app.dto.ProvinciaResponse;
import com.fonavi.faip.app.entity.Departamento;
import com.fonavi.faip.app.entity.Distrito;
import com.fonavi.faip.app.entity.Provincia;
import com.fonavi.faip.app.repository.DepartamentoRepository;
import com.fonavi.faip.app.repository.DistritoRepository;
import com.fonavi.faip.app.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl implements UbigeoService{

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    // 1. Departamentos con sus provincias (sin distritos aquí)
    @Override
    public List<DepartamentoResponse> getDepartamentos() {
        List<Departamento> deps = departamentoRepository.findAll();
        if(deps.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sin departamentos registrados");
        }
        return deps.stream()
                .sorted(Comparator.comparing(Departamento::getNombre))
                .map(this::toDepartamentoResponseSinDistritos)
                .toList();
    }

    // 2. Provincias de un departamento con distritos
    @Override
    public List<ProvinciaResponse> getProvincias(Long idDepartamento) {
        if(!departamentoRepository.existsById(idDepartamento)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Departamento no existe");
        }
        List<Provincia> provincias = provinciaRepository.findByDepartamentoIdOrderByNombreAsc(idDepartamento);
        if(provincias.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sin provincias registradas para el departamento " + idDepartamento);
        }
        return provincias.stream()
                .map(this::toProvinciaResponseConDistritos)
                .toList();
    }

    // 3. Distritos planos por provincia
    @Override
    public List<DistritoResponse> getDistrito(Long idProvincia) {
       if(!provinciaRepository.existsById(idProvincia)){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Provincia no existe");
       }
        List<Distrito> distritos = distritoRepository.findByProvinciaId(idProvincia);
        if (distritos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sin distritos registrados para la provincia " + idProvincia);
        }
        return distritos.stream()
                .sorted(Comparator.comparing(Distrito::getNombre))
                .map(this::toDistritoResponse)
                .toList();
    }

    // ======== MAPEOS PRIVADOS ========
    private DepartamentoResponse toDepartamentoResponseSinDistritos(Departamento dep){
        List<ProvinciaResponse> provincias = dep.getProvincias() == null ? List.of()
                : dep.getProvincias().stream()
                .sorted(Comparator.comparing(Provincia::getNombre))
                .map(p -> new ProvinciaResponse(
                        p.getId(),
                        p.getNombre(),
                        p.getUbigeo(),
                        null // en esta ruta no incluimos distritos
                )).toList();
        return new DepartamentoResponse(
                dep.getId(),
                dep.getNombre(),
                dep.getUbigeo(),
                provincias
        );
    }

    private ProvinciaResponse toProvinciaResponseConDistritos(Provincia p){
        List<DistritoResponse> distritos = p.getDistritos() == null ? List.of()
                : p.getDistritos().stream()
                .sorted(Comparator.comparing(Distrito::getNombre))
                .map(this::toDistritoResponse)
                .toList();
        return new ProvinciaResponse(
                p.getId(),
                p.getNombre(),
                p.getUbigeo(),
                distritos
        );
    }

    private DistritoResponse toDistritoResponse(Distrito d){
        return new DistritoResponse(d.getId(),d.getNombre(),d.getUbigeo());
    }
}
