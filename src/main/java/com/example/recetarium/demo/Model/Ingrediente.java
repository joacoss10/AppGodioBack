package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column
    private String nombre;
    @OneToMany(mappedBy = "ingrediente")
    private List<Utilizado> utilizados= new ArrayList<>();
    public Ingrediente() {}


    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUtilizados(List<Utilizado> utilizados) {
        this.utilizados = utilizados;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Utilizado> getUtilizados() {
        return utilizados;
    }



}
