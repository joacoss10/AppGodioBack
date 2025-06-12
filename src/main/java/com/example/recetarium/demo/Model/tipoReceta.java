package com.example.recetarium.demo.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class TipoReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipo;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tipoReceta", cascade = CascadeType.ALL)
    private List<Receta> recetas;

    // Getters y setters

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Receta> getRecetas() {
        return recetas;
    }

    public void setRecetas(List<Receta> recetas) {
        this.recetas = recetas;
    }
}
