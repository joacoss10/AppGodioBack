package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUnidad;

    @Column
    private String descripcion;

    @OneToMany(mappedBy ="unidad")
    private ArrayList<Utilizado> utilizado;

    @OneToMany(mappedBy = "unidadDestino")
    private ArrayList<Conversion> conversionDestino;
    @OneToMany(mappedBy = "unidadOrigen")
    private ArrayList<Conversion> conversionOrigen;

    public void setIdUnidad(Long idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUtilizados(ArrayList<Utilizado> utilizados) {
        this.utilizado = utilizados;
    }



    public Long getIdUnidad() {
        return idUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Utilizado> getUtilizados() {
        return utilizado;
    }


}
