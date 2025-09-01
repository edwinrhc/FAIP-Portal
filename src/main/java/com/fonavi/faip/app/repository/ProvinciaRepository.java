package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Provincia;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    List<Provincia> findByDepartamento_Id(Long departamentoId);

    // Para devolver provincias con distritos en un solo roundtrip
    @EntityGraph(attributePaths = {"distritos"})
    List<Provincia> findByDepartamentoIdOrderByNombreAsc(Long departamentoId);

    boolean existsById(Long id);

}
