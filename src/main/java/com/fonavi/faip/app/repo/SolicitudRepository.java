package com.fonavi.faip.app.repo;

import com.fonavi.faip.app.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    Optional<Solicitud> findByCodigo(String codigo);

    @Query("select count(s) from Solicitud s where  function('YEAR', s.fechaRegistro) = :year ")
    long countByYear(@Param("year") int year);

}
