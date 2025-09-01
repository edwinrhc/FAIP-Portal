package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Departamento;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    @Override
    @EntityGraph(attributePaths = {"provincias"})
    List<Departamento> findAll();
}
