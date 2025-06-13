package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngrediente;

    @Column
    private String nombre;

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUtilizados(ArrayList<Utilizado> utilizados) {
        this.utilizados = utilizados;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Utilizado> getUtilizados() {
        return utilizados;
    }

    @OneToMany(mappedBy = "ingrediente")
    private ArrayList<Utilizado> utilizados;


}
