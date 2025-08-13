package com.fonavi.faip.app.dto;

import jakarta.validation.constraints.*;

public class SolicitudForm {

    // Identidad
    @NotBlank(message = "Selecciona el tipo de solicitante")
    private String tipoSolicitante; // NATURAL | JURIDICA

    @NotBlank(message = "Nombres requeridos")
    private String nombres;

    private String apellidos; // opcional si es persona jurídica

    private String razonSocial; // requerido si es JURIDICA

    @NotBlank(message = "Tipo de documento requerido")
    private String tipoDocumento; // DNI | CE | PASAPORTE

    @NotBlank(message = "Número de documento requerido")
    @Pattern(regexp = "^[0-9A-Za-z]{6,15}$",
            message = "Documento inválido")
    private String numeroDocumento;

    // Contacto
    @NotBlank(message = "Correo requerido")
    @Email(message = "Correo inválido")
    private String email;

    @Pattern(regexp = "^[0-9 +()-]{6,20}$",
            message = "Teléfono inválido")
    private String telefono;

    // Dirección básica (texto libre para simplificar)
    private String direccion;
    private String distrito;
    private String provincia;
    private String departamento;
    private String pais = "Perú";

    // Preferencias y detalle
    @NotBlank(message = "Selecciona un medio de entrega")
    private String medioEntrega; // DIGITAL | FISICO | PRESENCIAL

    @NotBlank(message = "Describe la información solicitada")
    @Size(min = 10, message = "Incluye más detalles para ubicar la información")
    private String descripcion;

    private String observaciones; // opcional: finalidad / aclaraciones

    @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
    private boolean aceptaTerminos;

    // Getters & Setters
    // (puedes usar Lombok @Data si prefieres)
    // ...
    // === GENERADOS (o usa Lombok):
    public String getTipoSolicitante() {
        return tipoSolicitante;
    }

    public void setTipoSolicitante(String v) {
        this.tipoSolicitante = v;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String v) {
        this.nombres = v;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String v) {
        this.apellidos = v;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String v) {
        this.razonSocial = v;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String v) {
        this.tipoDocumento = v;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String v) {
        this.numeroDocumento = v;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        this.email = v;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String v) {
        this.telefono = v;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String v) {
        this.direccion = v;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String v) {
        this.distrito = v;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String v) {
        this.provincia = v;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String v) {
        this.departamento = v;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String v) {
        this.pais = v;
    }

    public String getMedioEntrega() {
        return medioEntrega;
    }

    public void setMedioEntrega(String v) {
        this.medioEntrega = v;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String v) {
        this.descripcion = v;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String v) {
        this.observaciones = v;
    }

    public boolean isAceptaTerminos() {
        return aceptaTerminos;
    }

    public void setAceptaTerminos(boolean v) {
        this.aceptaTerminos = v;
    }
}
