package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistritoRepository extends JpaRepository<Distrito, Long> {

    List<Distrito> findByProvincia_Id(Long provinciaId);

    List<Distrito> findByProvinciaId(Long provinciaId);
}
