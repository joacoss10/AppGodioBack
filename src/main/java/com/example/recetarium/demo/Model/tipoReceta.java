package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class tipoReceta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column
    private String descripcion;

    @OneToMany(mappedBy = "tipoReceta",cascade = CascadeType.ALL)
    private ArrayList<Receta> receta;

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReceta(ArrayList<Receta> receta) {
        this.receta = receta;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Receta> getReceta() {
        return receta;
    }
}
