package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ubigeo_distritos")
public class Distrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // bigint unsigned AUTO_INCREMENT

    @Column(name = "distrito", length = 150, nullable = false)
    private String nombre;

    @Column(name = "ubigeo", length = 6, nullable = false)
    private String ubigeo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id", nullable = false,
            foreignKey = @ForeignKey(name = "ubigeo_distritos_provincia_id_foreign"))
    private Provincia provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "ubigeo_distritos_departamento_id_foreign"))
    private Departamento departamento;
}
