package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReceta;

    @Column
    private String nombreReceta;
    private String descripcionReceta;
    private String porciones;
    private Date fechaCreacion;
    private int cantidadPersonas;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="Autor",referencedColumnName="idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private ArrayList<Calificacion> calificacion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="tipoReceta",referencedColumnName="idTipo")
    private tipoReceta tipoReceta;
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private ArrayList<FotoReceta> fotoReceta;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<Paso> paso;

    public void setFotoRecetas(ArrayList<FotoReceta> fotoRecetas) {
        this.fotoReceta = fotoRecetas;
    }

    public void setUtilizados(ArrayList<Utilizado> utilizados) {
        this.utilizado = utilizados;
    }

    public ArrayList<FotoReceta> getFotoRecetas() {
        return fotoReceta;
    }

    public ArrayList<Utilizado> getUtilizados() {
        return utilizado;
    }

    @OneToMany(mappedBy = "receta",cascade = CascadeType.ALL)
    private ArrayList<Utilizado> utilizado;

    @Lob
    @Column
    private byte[] imagen;

    public void setCalificacions(ArrayList<Calificacion> calificacions) {
        this.calificacion = calificacions;
    }

    public void setTipoReceta(com.example.recetarium.demo.Model.tipoReceta tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public ArrayList<Calificacion> getCalificacions() {
        return calificacion;
    }

    public com.example.recetarium.demo.Model.tipoReceta getTipoReceta() {
        return tipoReceta;
    }

    public void setIdReceta(Long idReceta) {
        this.idReceta = idReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public void setDescripcionReceta(String descripcionReceta) {
        this.descripcionReceta = descripcionReceta;
    }

    public void setPorciones(String porciones) {
        this.porciones = porciones;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Long getIdReceta() {
        return idReceta;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public String getDescripcionReceta() {
        return descripcionReceta;
    }

    public String getPorciones() {
        return porciones;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public byte[] getImagen() {
        return imagen;
    }
}
