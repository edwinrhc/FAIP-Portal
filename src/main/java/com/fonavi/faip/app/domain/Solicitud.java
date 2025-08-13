package com.fonavi.faip.app.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.java.Log;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud", indexes = {
        @Index(name="ix_solicitud_codigo",columnList = "codigo", unique = true)
})
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
    private String tipoSolicitante;  // Natural / Jurídica

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

    @Column(nullable = false, length = 20)
    private String medioEntrega;  // DIGITAL | FISICO | PRESENCIAL

    @Lob
    @Column(nullable = false)
    private String descripcion;

    @Lob
    private String observaciones;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
    public String getTipoSolicitante() { return tipoSolicitante; }
    public void setTipoSolicitante(String tipoSolicitante) { this.tipoSolicitante = tipoSolicitante; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
    public String getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getMedioEntrega() { return medioEntrega; }
    public void setMedioEntrega(String medioEntrega) { this.medioEntrega = medioEntrega; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }




}
