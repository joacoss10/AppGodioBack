package com.example.recetarium.demo.Model;

import com.example.recetarium.demo.Model.Enums.EstadoReceta;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private Date fechaCreacion;
    private int cantidadPersonas;

    @Enumerated(EnumType.STRING)
    private EstadoReceta estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Autor", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoReceta", referencedColumnName = "idTipo")
    private TipoReceta tipoReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Calificacion> calificacion;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<FotoReceta> fotoReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Paso> paso;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Utilizado> utilizado;

    @Lob
    @Column
    private byte[] imagen;

    // Getters y Setters

    public Long getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public String getPorciones() {
        return porciones;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoReceta getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(TipoReceta tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public List<Calificacion> getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(List<Calificacion> calificacion) {
        this.calificacion = calificacion;
    }

    public List<FotoReceta> getFotoReceta() {
        return fotoReceta;
    }

    public void setFotoReceta(List<FotoReceta> fotoReceta) {
        this.fotoReceta = fotoReceta;
    }

    public List<Paso> getPaso() {
        return paso;
    }

    public void setPaso(List<Paso> paso) {
        this.paso = paso;
    }

    public List<Utilizado> getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(List<Utilizado> utilizado) {
        this.utilizado = utilizado;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
