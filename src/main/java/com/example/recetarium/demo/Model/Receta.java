package com.example.recetarium.demo.Model;

import com.example.recetarium.demo.Model.Enums.EstadoReceta;
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
    //Enum con estado de la receta
    @Enumerated(EnumType.STRING)
    private EstadoReceta estado = EstadoReceta.En_Espera;
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn (name="Autor",referencedColumnName="idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private List<Calificacion> calificacion = new ArrayList<>();
    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private List<FotoReceta> fotoReceta = new ArrayList<>();

    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private List<Paso> paso = new ArrayList<>();
    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private List<Utilizado> utilizado = new ArrayList<>();
    @OneToMany(mappedBy = "receta", orphanRemoval = true)
    private List<RecetaFavorito> recetaFavoritos = new ArrayList<>();

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column
    private byte[] imagen;

    public List<RecetaFavorito> getRecetaFavoritos() {
        return recetaFavoritos;
    }

    public void setRecetaFavoritos(List<RecetaFavorito> recetaFavoritos) {
        this.recetaFavoritos = recetaFavoritos;
    }

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

    public void setFotoRecetas(List<FotoReceta> fotoRecetas) {
        this.fotoReceta = fotoRecetas;
    }

    public void setUtilizados(List<Utilizado> utilizados) {
        this.utilizado = utilizados;
    }

    public List<FotoReceta> getFotoRecetas() {
        return fotoReceta;
    }

    public List<Utilizado> getUtilizados() {
        return utilizado;
    }

    public void setCalificacion(List<Calificacion> calificacions) {
        this.calificacion = calificacions;
    }

    public List<Calificacion> getCalificacion() {
        return calificacion;
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

    public List<Paso> getPaso() {
        return paso;
    }

    public void setPaso(List<Paso> paso) {
        this.paso = paso;
    }
}
