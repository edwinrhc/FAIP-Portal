package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    List<Provincia> findByDepartamento_Id(Integer departamentoId);

}
