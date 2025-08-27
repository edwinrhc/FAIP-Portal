package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ubigeo_departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // bigint unsigned AUTO_INCREMENT

    @Column(name = "departamento", length = 50, nullable = false)
    private String nombre;

    @Column(name = "ubigeo", length = 2, nullable = false)
    private String ubigeo;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Provincia> provincias;
}
