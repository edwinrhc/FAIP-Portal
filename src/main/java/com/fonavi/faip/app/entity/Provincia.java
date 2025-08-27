package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ubigeo_provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // bigint unsigned AUTO_INCREMENT

    @Column(name = "provincia", length = 100, nullable = false)
    private String nombre;

    @Column(name = "ubigeo", length = 4, nullable = false)
    private String ubigeo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "ubigeo_provincias_departamento_id_foreign"))
    private Departamento departamento;

    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Distrito> distritos;
}