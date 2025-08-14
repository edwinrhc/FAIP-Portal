package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Solicitud;
import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    @Query("SELECT s FROM Solicitud s WHERE s.estado = 'REGISTRADA' AND s.fechaLimite = :fechaAviso")
    List<Solicitud> findSolicitudesPorVencer(LocalDate fechaAviso);

    Optional<Solicitud> findByCodigo(String codigo);


}
