package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
public class Utilizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilizado;

    @Column
    private float cantidad;
    private String observaciones;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="receta",referencedColumnName = "idReceta")
    private Receta receta;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="unidad",referencedColumnName = "idUnidad")
    private Unidad unidad;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="ingrediente",referencedColumnName = "idIngrediente")
    private Ingrediente ingrediente;

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdUtilizado() {
        return idUtilizado;
    }

    public float getCantidad() {
        return cantidad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Receta getReceta() {
        return receta;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

}
