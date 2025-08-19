package com.fonavi.faip.app.repository;

import com.fonavi.faip.app.entity.Permiso;
import com.fonavi.faip.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    // Método generado por Spring Data JPA
    List<Permiso> findByRolAndRecurso(Role rol, String recurso);


}
