package com.example.recetarium.demo.Model;

import jakarta.persistence.*;

@Entity
public class Utilizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilizado;

    @Column
    private int cantidad;
    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="receta",referencedColumnName = "idReceta")
    private Receta receta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="unidad",referencedColumnName = "idUnidad")
    private Unidad unidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ingrediente",referencedColumnName = "idIngrediente")
    private Ingrediente ingrediente;
}
