package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Solicitud;
import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    Optional<Solicitud> findByCodigo(String codigo);


}
