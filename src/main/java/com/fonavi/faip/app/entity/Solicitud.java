package com.fonavi.faip.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(
        name = "solicitud",
        indexes = @Index(name = "ix_solicitud_codigo", columnList = "codigo", unique = true)
)
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String codigo; // API-YYYY-00001

    @Column(nullable = false, name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(nullable = false, name = "fecha_limite")
    private LocalDate fechaLimite; // <- Plazo máximo según Ley

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EstadoSolicitud estado = EstadoSolicitud.REGISTRADA;

    // Datos del solicitante
    @Column(nullable = false, length = 100, name = "tipo_solicitante")
    private String tipoSolicitante; // Persona Natural / Jurídica

    @Column(nullable = false, length = 20, name = "tipo_documento")
    private String tipoDocumento;

    @Column(nullable = false, length = 20, name = "numero_documento")
    private String numeroDocumento;

    @Column(length = 100)
    private String nombres;

    @Column(length = 100, name = "apellidos_paterno")
    private String apellidosPaterno;

    @Column(length = 100, name = "apellidos_materno")
    private String apellidosMaterno;

    @Column(length = 150, name = "razon_social")
    private String razonSocial;

    @Column(length = 50)
    private String pais = "Perú";

/*    @Column(length = 50)
    private String departamento;

    @Column(length = 50)
    private String provincia;

    @Column(length = 50)
    private String distrito;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false,
            foreignKey = @ForeignKey(name = "solicitud_departamento_id_fk"))
    private Departamento departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id", nullable = false,
            foreignKey = @ForeignKey(name = "solicitud_provincia_id_fk"))
    private Provincia provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distrito_id", nullable = false,
            foreignKey = @ForeignKey(name = "solicitud_distrito_id_fk"))
    private Distrito distrito;



    @Column(length = 200)
    private String direccion;

    // Contacto
    @Column(length = 150)
    private String email;

    @Column(length = 25)
    private String telefono;

    private Integer edad;
    private String sexo;

    @Column(name = "area_pertenece")
    private String areaPertenece;

    // Descripción y observaciones
    @Lob
    @Column(nullable = false)
    private String descripcion; // CLOB

    @Lob
    private String observaciones; // CLOB

    // Archivo adjunto
    @Lob
    @Column(name = "archivo_adjunto")
    private byte[] archivoAdjunto; // BLOB

    // Datos de entrega
    @Column(nullable = false, length = 20, name = "medio_entrega")
    private String medioEntrega; // DIGITAL | FISICO | PRESENCIAL

    @Column(nullable = false, name = "modalidad_notificacion")
    private String modalidadNotificacion;

    // Validación
    @AssertTrue(message = "Debes aceptar los términos y el tratamiento de datos")
    @Column(nullable = false, name = "acepta_terminos")
    private boolean aceptaTerminos;

    @PostPersist
    public void generarCodigoUnico() {
        if (this.codigo == null) {
            String año = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));
            this.codigo = String.format("FAIP-%s-%04d", año, this.id);
        }
    }
}
