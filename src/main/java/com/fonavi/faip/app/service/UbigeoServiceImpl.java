package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.DepartamentoResponse;
import com.fonavi.faip.app.dto.DistritoResponse;
import com.fonavi.faip.app.dto.ProvinciaResponse;
import com.fonavi.faip.app.repository.DepartamentoRepository;
import com.fonavi.faip.app.repository.DistritoRepository;
import com.fonavi.faip.app.repository.ProvinciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UbigeoServiceImpl implements UbigeoService{

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    @Override
    public List<DepartamentoResponse> getDepartamentos() {
        List<DepartamentoResponse> data = departamentoRepository
                .findAll()
                .stream()
                .map(dep -> new DepartamentoResponse(
                        dep.getId(),
                        dep.getNombre(),
                        dep.getUbigeo()
                )).toList() ;
        if( data.isEmpty() )
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Sin departamentos registrados");
        }
        return data;
    }

    @Override
    public List<ProvinciaResponse> getProvincias(Integer idDepartamento) {
        return List.of();
    }

    @Override
    public List<DistritoResponse> getDistrito(Integer idProvincia) {
        return List.of();
    }
}
