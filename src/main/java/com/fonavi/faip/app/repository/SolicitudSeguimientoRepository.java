package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.SolicitudSeguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudSeguimientoRepository extends JpaRepository<SolicitudSeguimiento, Long> {
//    findBySolicitudCodigoOrderByFechaCambioAsc
//List<SolicitudSeguimiento> findBySolicitudCodigoOrderByFechaRegistroAsc(String codigo);

    @Query("SELECT s FROM SolicitudSeguimiento s WHERE s.solicitud.codigo = :codigo ORDER BY s.fechaRegistro ASC")
    List<SolicitudSeguimiento> buscarPorCodigoSolicitud(@Param("codigo") String codigo);

}
