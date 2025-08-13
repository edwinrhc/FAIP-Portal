package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud", indexes = {
        @Index(name="ix_solicitud_codigo",columnList = "codigo", unique = true)
})
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo; // API-YYYY-00001

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado = EstadoSolicitud.REGISTRADA;

    // -- Datos principales (simplificados) --//
    @Column(nullable = false, length = 100)
    private String tipoSolicitante;  // Persona Natural / Persona Jurídica

    @Column(nullable = false, length = 100)
    private String nombres;

    @Column(length = 100)
    private String apellidos;

    @Column(length = 150)
    private String razonSocial;

    @Column(nullable = false, length = 20)
    private String tipoDocumento;

    @Column(nullable = false, length = 20)
    private String numeroDocumento;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(length = 25)
    private String telefono;

    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String pais = "Perú";

    @Column(nullable = false, length = 20)
    private String medioEntrega;  // DIGITAL | FISICO | PRESENCIAL

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Lob
    private String observaciones;

    @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
    private boolean aceptaTerminos;


}
