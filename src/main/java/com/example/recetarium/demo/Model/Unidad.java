package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "unidad")
    private List<Utilizado> utilizado = new ArrayList<>();

    @OneToMany(mappedBy = "unidadDestino")
    private List<Conversion> conversionDestino = new ArrayList<>();
    @OneToMany(mappedBy = "unidadOrigen")
    private List<Conversion> conversionOrigen = new ArrayList<>();

    public Unidad() {}

    public void setIdUnidad(Long idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUtilizado(List<Utilizado> utilizado) {
        this.utilizado = utilizado;
    }

    public void setConversionDestino(List<Conversion> conversionDestino) {
        this.conversionDestino = conversionDestino;
    }

    public void setConversionOrigen(List<Conversion> conversionOrigen) {
        this.conversionOrigen = conversionOrigen;
    }

    public List<Utilizado> getUtilizado() {
        return utilizado;
    }

    public List<Conversion> getConversionDestino() {
        return conversionDestino;
    }

    public List<Conversion> getConversionOrigen() {
        return conversionOrigen;
    }

    public Long getIdUnidad() {
        return idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }



}
