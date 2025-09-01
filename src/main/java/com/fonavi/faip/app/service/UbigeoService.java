package com.fonavi.faip.app.service;

import com.fonavi.faip.app.dto.DepartamentoResponse;
import com.fonavi.faip.app.dto.DistritoResponse;
import com.fonavi.faip.app.dto.ProvinciaResponse;

import java.util.List;

public interface UbigeoService {

        List<DepartamentoResponse> getDepartamentos();
        List<ProvinciaResponse> getProvincias(Integer idDepartamento);
        List<DistritoResponse>  getDistrito(Integer idProvincia);
}
