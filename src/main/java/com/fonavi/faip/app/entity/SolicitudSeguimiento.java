package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "solicitud_seguimiento")
@Data
public class SolicitudSeguimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = "solicitud_id",                 // Nombre de la columna en esta tabla
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_solicitud_seguimiento_solicitud") // Nombre personalizado del FK
    )
    private Solicitud solicitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado;

    @Lob
    private String observacion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false, length = 100)
    private String usuarioAccion; // Quién hizo el cambio (admin o sistema)
}
