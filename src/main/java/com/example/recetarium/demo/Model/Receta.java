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
    private ArrayList<Calificacion> calificacion;
    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private ArrayList<FotoReceta> fotoReceta;

    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private List<Paso> paso;

    public EstadoReceta getEstado() {
        return estado;
    }

    public void setEstado(EstadoReceta estado) {
        this.estado = estado;
    }

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

    @OneToMany(mappedBy = "receta",orphanRemoval = true)
    private ArrayList<Utilizado> utilizado;

    @Lob
    @Column
    private byte[] imagen;

    public void setCalificacions(ArrayList<Calificacion> calificacions) {
        this.calificacion = calificacions;
    }

    public ArrayList<Calificacion> getCalificacions() {
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
}
